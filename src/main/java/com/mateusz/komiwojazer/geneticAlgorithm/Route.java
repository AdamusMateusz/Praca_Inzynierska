package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mateusz.komiwojazer.utils.Request;

public class Route implements Cloneable {

	private final int[] cities;
	private final double quality;
	private final boolean changed;

	public Route(final int[] cities) {
		this.cities = cities;
		this.quality = 0;
		this.changed = false;
	}
	
	public Route(final int[] cities,final double quality, final boolean changed) {
		this.cities = cities;
		this.quality = quality;
		this.changed = changed;
	}

	public Route(Route route, double rate) {
		this.cities = route.getCities();
		this.quality = rate;
		this.changed = route.isChanged();
	}

	public static Route generateRandomRoute(final int quantity) {
		int[] ret = new int[quantity];
		IntStream.range(0, quantity).forEach(i -> ret[i] = (int) (Math.random() * quantity));

		return new Route(ret);
	}

	public Route rate(final double[][] distanceMatrix) {
		final int length = cities.length;

		for (int i = 0; i < length - 1; i++)
			for (int j = i + 1; j < length; j++)
				if (cities[i] == cities[j])
					return this.withRate(-1);

		final double r = IntStream.range(1, length)
				.mapToDouble(i -> distanceMatrix[cities[i - 1]][cities[i]])
				.sum() + distanceMatrix[cities[length - 1]][cities[0]];

		return this.withRate(r );
	}
	
	public static double ratePermutation(final int[] route, final double[][] distanceMatrix) {
		return IntStream.range(1, route.length)
				.mapToDouble(i -> distanceMatrix[route[i - 1]][route[i]])
				.sum() + distanceMatrix[route[route.length - 1]][route[0]] ;
	}
	
	public int[] getCities() {
		return cities;
	}

	public double getQuality() {
		return quality;
	}
	
	public boolean isChanged() {
		return changed;
	}
	
	public Route withChanged(final boolean changed){
		return new Route(cities, quality, changed);
	}
	
	public Route withRate(final double quality){
		return new Route(cities, quality, changed);
	}

	public static List<Route> sortRoutes(List<Route> routes) {

		Map<Boolean, List<Route>> map = routes.stream().collect(Collectors.groupingBy((r) -> r.getQuality() != -1));

		List<Route> list = map.getOrDefault(true,new ArrayList<>())
				.stream()
				.sorted((r1,r2)->Double
						.compare(r1.getQuality(), r2.getQuality()))
				.collect(Collectors.toList());
		
		list.addAll(map.getOrDefault(false,new ArrayList<>()));
		
		return list;
	}

	public static List<Route> cross(Route r1, Route r2, Request args) {

		final int[] oldCities1 = r1.getCities();
		final int[] oldCities2 = r2.getCities();
		
		if(args.hasCrossingChromosomePossibility()){
			
			final int[] cities1 = new int[oldCities1.length];
			final int[] cities2 = new int[oldCities2.length];
			boolean changed = false;
			
			for(int i = 0; i < cities1.length; i++){
				if(args.hasCrossingGenePossibility()){
					changed = true;
					cities1[i]= oldCities2[i];
					cities2[i]= oldCities1[i];
				}else{
					cities1[i]= oldCities1[i];
					cities2[i]= oldCities2[i];
				}
			}
			return Arrays.asList(new Route(cities1).withChanged(changed),new Route(cities2).withChanged(changed));
			
		}else{
			return Arrays.asList(new Route(oldCities1),new Route(oldCities2));
		}
	}

	public Route mutate(Request args) {

		if(args.hasMutationChromosomePossibility()){
			final int[] newCities = new int[cities.length];
			boolean changed = false;
			
			for(int i =0; i < cities.length; i++){
				if(args.hasMutationGenePossibility()){
					changed = true;
					newCities[i] = ThreadLocalRandom.current().nextInt(cities.length);

				}else{
					newCities[i] = cities[i];
				}
			}
			return new Route(newCities).withChanged(changed);
		}else{
			return this;
		}
	}
	
}
