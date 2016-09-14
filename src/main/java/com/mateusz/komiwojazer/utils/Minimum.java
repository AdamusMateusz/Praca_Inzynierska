package com.mateusz.komiwojazer.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.util.SocketUtils;

import com.mateusz.komiwojazer.geneticAlgorithm.Route;

public class Minimum {

	//array with factorial values
	private static final long[] factorials;
	
	static{
		//20! - the largest n-factorial value that can fit in a long (max 2^63)
		//42!   1405006117752879898543142606244511569936384000000000 (~number of atoms on earth)
		factorials = new long[21];		
		IntStream.rangeClosed(0, 20)
		.forEach(i->factorials[i]=LongStream.rangeClosed(2, i).reduce(1, (a, b) -> a * b));
	}
	
	public static double calculateMinimum(final double[][] distanceMatrix) throws IllegalArgumentException {
		int n = distanceMatrix.length;
		
		if (n > 22) 
			throw new IllegalArgumentException(n + " is out of range");
		
		return IntStream.range(0, n)
		.mapToObj(integer-> calculateMinimumHelper(integer,distanceMatrix))
		.collect(Collectors.toList()).stream()
		.map(future->{
			try {
				return future.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Double.MAX_VALUE;
		})
		.reduce(Double.MAX_VALUE,(d1,d2)->Math.min(d1, d2));
		
	}
	
	public static CompletableFuture<Double> calculateMinimumHelper(final int current,final double[][] distanceMatrix){
		return CompletableFuture.supplyAsync(()->{
			//result
			double minimum = Double.MAX_VALUE;
			//length == cities quantity
			int length = distanceMatrix.length;
			
			//array containing information about visiting order of cities
			int[] route = new int[length];
			route[0] = current;
			
			//Array with indexes of cities not included in route yet
			List<Integer> cities = new ArrayList<Integer>();
			
			//Filling array with available cities
			int iterator=0;
			while(cities.size() < length - 1){
				//current city is already in route
				if(iterator == current)
					iterator++;
				cities.add(new Integer(iterator++));
			}
			
			
			for(long i =0; i < factorials[length-1]; i++){

				LinkedList<Integer> input = new LinkedList<Integer>(cities);
				
				long number = i;
				
				for(int j = 1; j <route.length; j++){
					long subFactorial = factorials[input.size()-1];
					route[j] = input.remove((int) (number / subFactorial));
					number = (int)(number%subFactorial);
				}
				minimum = Math.min(Route.ratePermutation(route, distanceMatrix),minimum);
			}

			return minimum;
		});
	}
	
}
