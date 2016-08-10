package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mateusz.komiwojazer.utils.FilesService;
import com.mateusz.komiwojazer.utils.Request;


public class Task {

	private final List<City> cities;
	private final double[][] distanceMatrix;
	private final List<Route> parents;
	private final boolean complete;

	private Task(final Task t) {
		this.cities = t.getCities();
		this.distanceMatrix = t.getDistanceMatrix();
		this.parents = t.getParents();
		this.complete = t.isComplete();
	}

	private Task(final List<City> cities,final double[][] distanceMatrix,final List<Route> parents,final boolean complete) {
		this.cities = cities;
		this.distanceMatrix = distanceMatrix;
		this.parents = parents;
		this.complete = complete;
	}

	private Task(final List<City> cities,final double[][] distanceMatrix,final List<Route> parents) {
		this.cities = cities;
		this.distanceMatrix = distanceMatrix;
		this.parents = parents;
		this.complete = false;
	}

	private Task withParents(final List<Route> parents) {
		return new Task(cities, distanceMatrix, parents, complete);
	}

	/**
	 * Crates and starts new Task
	 * 
	 * @param args - Arguments from client which provides necessary informations
	 * @return Newly created, probably not finished task
	 */
	
	public static CompletableFuture<Task> produceTask(final Request args) {
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


			// Create and rate  parents population
			List<Route> parents = Stream
					.generate(() -> args.getCitiesQuantity())
					.parallel()
					.limit(args.getParents())
					.map(Route::generateRandomRoute)
					.map(route -> route.rate(distanceMatrix))
					.collect(Collectors.toList());
			
			parents = Route.sortRoutes(parents);
		
			// return new started task
			return new Task(cities, distanceMatrix, parents);
			
		});
	}

	/**
	 * Proceed started task
	 * 
	 * @param args - Arguments from client request which provides necessary informations
	 * @return task
	 */
	public Task proceed(final Request args) {
		List<Route> kidsList = new ArrayList<>();;
		int parentIndex = 0;
		//TODO mozna zrobic to na Completable Future, wtedy jendo zadanie
		//³adnie pyknie na wszystkich rdzeniach
		do{
		//cross
			final Route r1 = parents
					.get(parentIndex < parents.size() ? parentIndex++ : (int) ((Math.random() * parents.size()) - 1)); 
			final Route r2 = parents.get((int) ((Math.random() * parents.size()) - 1));
			
			List<Route> kids = Route.cross(r1,r2,args);
			kidsList.addAll(kids);
			
		}while(kidsList.size() < args.getKids());
		
		kidsList = kidsList.subList(0, args.getKids());
		
		//mutate
		kidsList.replaceAll(route-> route.mutate(args));
		
		//Check change rate, and returns old task if needed
		double ratio = ((double)(kidsList.stream().filter(route -> route.isChanged()).count()) / (double)(args.getKids())*100.0);
		if (ratio < args.getChange()){
			return this;		
		}
		
		//Rate population
		kidsList.replaceAll(route->route.rate(distanceMatrix));
		
		//Add parents to population
		if(args.isUseParents())
			kidsList.addAll(parents);
		
		//Sort kids
		kidsList = Route.sortRoutes(kidsList);
		
		//Selection
		kidsList = kidsList.subList(0, args.getParents());
		
		//Save minimal value of fitness function
		if(args.isSaveFittingFunctionValue()){
			FilesService.save(args.getId(),kidsList.get(0).getQuality());
		}

		return this.withParents(kidsList);
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
	
	public double getQuality(){
		return parents.get(0).getQuality();
	}

}
