package com.mateusz.komiwojazer.geneticAlgorithm;

public class MinAndMax {
	private final double min;
	private final double max;

	public MinAndMax(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public static MinAndMax getMinAndMax(Task task) {

		final int quantity = task.getCities().size();
		int[] cities = new int[quantity];
		double min = Integer.MAX_VALUE, max = -1, rate = 0;
		double[][] distanceMatrix = task.getDistanceMatrix();

		for (int i = 0; i < quantity; i++) {
			cities[i] = i;
		}
		cities[quantity - 1] = quantity - 1;

		while (!isLastElement(cities)) {
			setNextElement(cities);

			if (isValidRoute(cities)) {
				rate = 0;
				for (int i = 1; i < quantity; i++) {
					rate += distanceMatrix[cities[i - 1]][cities[i]];
				}
				max = max < rate ? rate : max;
				min = min > rate ? rate : min;
				
			}
		}

		return new MinAndMax(min, max);
	}

	public static boolean isValidRoute(int[] cities) {
		final int length = cities.length;
		for (int i = 0; i < length - 1; i++)
			for (int j = i + 1; j < length; j++)
				if (cities[i] == cities[j])
					return false;
		return true;
	}

	public static boolean isLastElement(int[] cities) {
		final int length = cities.length;
		for (int i = 0; i < length; i++) {
			if (cities[(cities.length - 1) - i] != i)
				return false;
		}
		return true;
	}

	public static void setNextElement(int[] cities) {
		final int max = cities.length - 1;
		cities[max]++;

		for (int i = max; i > 0; i--) {
			if (cities[i] > max) {
				cities[i] = 0;
				cities[i - 1]++;
			}
		}
	}

	@Override
	public String toString() {
		return String.format("(%f,%f)", min, max);
	}

}
