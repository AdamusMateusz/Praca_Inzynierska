package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Minimum {

	//array with factorial values
	private static final long[] factorials;
	
	static{
		//20! - the largest n-factorial value that can fit in a long (max 2^63)
		//42!   1405006117752879898543142606244511569936384000000000 (~number of atoms on earth)
				
		factorials = new long[21];
		factorials[0] = 1L;
		factorials[1] = 1L;
		factorials[2] = 2L;
		factorials[3] = 6L;
		factorials[4] = 24L;
		factorials[5] = 120L;
		factorials[6] = 720L;
		factorials[7] = 5040L;
		factorials[8] = 40320L;
		factorials[9] = 362880L;
		factorials[10] = 3628800L;
		factorials[11] = 39916800L;
		factorials[12] = 479001600L;
		factorials[13] = 6227020800L;
		factorials[14] = 87178291200L;
		factorials[15] = 1307674368000L;
		factorials[16] = 20922789888000L;
		factorials[17] = 355687428096000L;
		factorials[18] = 6402373705728000L;
		factorials[19] = 121645100408832000L;
		factorials[20] = 2432902008176640000L;
	}
	
	public static double calculateMinimum(final double[][] distanceMatrix) throws IllegalArgumentException {
		int n = distanceMatrix.length;
		
		if (n > 22) 
			throw new IllegalArgumentException(n + " is out of range");
		
		List<CompletableFuture<Double>> collection = IntStream.range(0, n)
		.mapToObj(integer-> calculateMinimumHelper(integer,distanceMatrix))
		.collect(Collectors.toList());
		
		return collection.stream()
				.map(future->{
			try {
				return future.get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
				minimum = Math.min(Route.rate(route, distanceMatrix),minimum);
			}
			
			return minimum;
		});
	}
	
}
