package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.stream.IntStream;

public class Route implements Cloneable {

	private final int[] cities;
	private final double quality;

	public Route(final int[] cities) {
		this.cities = cities;
		this.quality = 0;
	}

	public Route(final int[] cities,final double quality) {
		this.cities = cities;
		this.quality = quality;
	}

	public static Route generateRandomRoute(final int quantity) {
		int[] ret = new int[quantity];
		IntStream
		.range(0, quantity)
		.forEach(i->ret[i]=(int) (Math.random() * quantity));
		
		return new Route(ret);
	}

	public Route rate(final double[][] distanceMatrix) {
		final int length = cities.length;
		
		for (int i = 0; i < length - 1; i++)
			for (int j = i+1; j < length; j++)
				if (cities[i] == cities[j])
					return new Route(cities,-1);
		
		final double r = IntStream
				.range(1, length)
				.mapToDouble(i->distanceMatrix[cities[i-1]][cities[i]])
				.sum();
		
		return new Route(cities,r + distanceMatrix[0][length-1]);
	}

	public int[] getCities() {
		return cities;
	}

	public double getQuality() {
		return quality;
	}
}
