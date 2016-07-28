package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task {

	private final List<City> cities;
	private final double[][] distanceMatrix;
	private final List<Route> parents;
	private final boolean complete;

	private Task(Task t) {
		this.cities = t.getCities();
		this.distanceMatrix = t.getDistanceMatrix();
		this.parents = t.getParents();
		this.complete = t.isComplete();
	}

	private Task(List<City> cities, double[][] distanceMatrix, List<Route> parents, boolean complete) {
		this.cities = cities;
		this.distanceMatrix = distanceMatrix;
		this.parents = parents;
		this.complete = complete;
	}

	private Task(List<City> cities, double[][] distanceMatrix, List<Route> parents) {
		this.cities = cities;
		this.distanceMatrix = distanceMatrix;
		this.parents = parents;
		this.complete = false;
	}

	@SuppressWarnings("unused")
	private Task withParents(List<Route> parents, boolean complete) {
		return new Task(cities, distanceMatrix, parents, complete);
	}

	@SuppressWarnings("unused")
	private Task withParents(List<Route> parents) {
		return new Task(cities, distanceMatrix, parents, complete);
	}

	/**
	 * Crates and starts new Task
	 * 
	 * @param args
	 *            - Arguments from client which provides necessary informations
	 * @return Newly created, probably not finished task
	 */
	
	public static CompletableFuture<Task> produceTask(final ArgumentsSet args) {
		return CompletableFuture.supplyAsync(() -> {
			// Generate Cities
			final List<City> cities = Stream
					.generate(City::randomCity)
					.limit(args.getCitiesQuantity())
					.collect(Collectors.toList());

			// Calculate DistanceMatrix
			final int quantity = args.getCitiesQuantity();
			final double[][] distanceMatrix = new double[quantity][quantity];

			// Returns length of a vector
			final BiFunction<City, City, Double> f = (c1, c2) -> {
				return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) + Math.pow(c2.getY() - c1.getY(), 2));
			};

			IntStream.range(0, quantity).parallel().forEach(i -> IntStream.range(0, quantity)
					.forEach(j -> distanceMatrix[i][j] = f.apply(cities.get(i), cities.get(j))));


			// Create and rate parents population
			final List<Route> parents = Stream
					.generate(() -> args.getCitiesQuantity())
					.parallel()
					.limit(args.getParents())
					.map(Route::generateRandomRoute)
					.map(route -> route.rate(distanceMatrix))
					.collect(Collectors.toList());

			// return new started task
			return new Task(cities, distanceMatrix, parents);
		});
	}

	public Task proceed(final ArgumentsSet args) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isComplete() {
		return complete;
	}

	public List<City> getCities() {
		return cities;
	}

	public double[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	public List<Route> getParents() {
		return parents;
	}

}
