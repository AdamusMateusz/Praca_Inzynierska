package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mateusz.komiwojazer.utils.FittingValueService;
import com.mateusz.komiwojazer.utils.Minimum;
import com.mateusz.komiwojazer.utils.Request;


public class Task {

	private final List<City> cities;
	private final double[][] distanceMatrix;
	private final List<Route> parents;
	private final double minimum;

	private Task(final Task t) {
		this.cities = t.getCities();
		this.distanceMatrix = t.getDistanceMatrix();
		this.parents = t.getParents();
		this.minimum = t.getMinimum();
	}

	private Task(final List<City> cities,final double[][] distanceMatrix,final List<Route> parents) {
		this.cities = cities;
		this.distanceMatrix = distanceMatrix;
		this.parents = parents;
		this.minimum = -2;
	}

	private Task(final List<City> cities,final double[][] distanceMatrix,final List<Route> parents,final double minimum) {
		this.cities = cities;
		this.distanceMatrix = distanceMatrix;
		this.parents = parents;
		this.minimum = minimum;
	}

	private Task withParents(final List<Route> parents) {
		return new Task(cities, distanceMatrix, parents,minimum);
	}

	/**
	 * Crates and starts new Task
	 * 
	 * @param args - Arguments from client which provides necessary informations
	 * @return Newly created, probably not finished task
	 */
	
	public static CompletableFuture<Task> produceTask(final Request args) {
		return CompletableFuture.supplyAsync(() -> {
			
				// Returns length of a vector
				final BiFunction<City, City, Double> f = (c1, c2) -> 
				 Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) + Math.pow(c2.getY() - c1.getY(), 2));
				
				// Generate Cities
				final List<City> cities = new LinkedList<>();
				
				while(cities.size() < args.getCitiesQuantity()){
					
					final City c = City.randomCity();
					
					int j = 0;
					
					while (j < cities.size()) {
						final City tmp = cities.get(j);
						if (f.apply(tmp, c) < City.DIAMETER * 2){
							break;
						}
						else{
							j++;
						}
					}
					if (j == cities.size()) {
						cities.add(c);
					}
					
				}
				
				// Calculate DistanceMatrix
				final int quantity = args.getCitiesQuantity();
				final double[][] distanceMatrix = new double[quantity][quantity];
				
				IntStream.range(0, quantity).forEach(i -> IntStream.range(0, quantity)
						.forEach(j -> distanceMatrix[i][j] = f.apply(cities.get(i), cities.get(j))));

				//calculate minimum if needed in heuristic way
				CompletableFuture<Double> minimum = (args.isHeuristic() && quantity <= 22) ?
						CompletableFuture.supplyAsync(()->Minimum.calculateMinimum(distanceMatrix)):
						CompletableFuture.completedFuture(new Double(-2));
						
				// Create and rate  parents population
				List<Route> parents = Stream
						.generate(() -> args.getCitiesQuantity())
						.parallel()
						.limit(args.getParents())
						.map(Route::generateRandomRoute)
						.map(route -> route.rate(distanceMatrix))
						.collect(Collectors.toList());
				
				parents = Route.sortRoutes(parents);
				
				//Save minimal value of fitting function
				if(args.isSaveFittingFunctionValue()){
					FittingValueService.saveFirst(args.getId(),parents.get(0).getQuality());
				}
				
				// return new task
				try {
					return new Task(cities, distanceMatrix, parents,minimum.get());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new Task(cities, distanceMatrix, parents);
		});
	}

	/**
	 * Proceeds started task
	 * 
	 * @param args - Arguments from client request which provides necessary informations
	 * @return task
	 */
	public Task proceed(final Request args) {
		
		List<CompletableFuture<List<Route>>> futureKidsList = new ArrayList<>();;
		int parentIndex = 0;

		do{
		//cross
			final Route r1 = parents
					.get(parentIndex < parents.size() ? parentIndex++ : (int) ((Math.random() * parents.size()) - 1)); 
			final Route r2 = parents.get((int) ((Math.random() * parents.size()) - 1));
			
			futureKidsList.add(CompletableFuture.supplyAsync(()->Route.cross(r1,r2,args)));
			
		}while(futureKidsList.size() /2 < args.getKids());
		
		List<Route> kidsList = futureKidsList.stream().flatMap(future-> {
			try {
				return future.get().stream();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList()).subList(0, args.getKids());

		
		//mutate
		kidsList.replaceAll(route-> route.mutate(args));
		
		//Check change rate, and return old task if needed
		double ratio = 
		((double)(kidsList.stream().filter(route -> route.isChanged()).count()) / (double)(args.getKids())*100.0);
	
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
		
		//Select parent population
		kidsList = kidsList.subList(0, Math.min(args.getParents(), kidsList.size()));
		
		//Save minimal value of fitting function
		if(args.isSaveFittingFunctionValue()){
			FittingValueService.save(args.getId(),kidsList.get(0).getQuality());
		}

		return this.withParents(kidsList);
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

	public double getMinimum() {
		return minimum;
	}

	public boolean isCompleted() {
		return getQuality() == minimum;
	}

}
