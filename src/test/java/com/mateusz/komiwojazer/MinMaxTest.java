package com.mateusz.komiwojazer;

import java.util.concurrent.ExecutionException;
import java.util.stream.LongStream;

import org.junit.Test;

import com.mateusz.komiwojazer.geneticAlgorithm.Minimum;
import com.mateusz.komiwojazer.geneticAlgorithm.Task;
import com.mateusz.komiwojazer.utils.Request;

public class MinMaxTest {

//	@Test
	public void test1(){
	for(int i = 0 ; i <= 20 ; i++)
	System.out.format("factorialValues[%d] = %dL;%n", i,LongStream.rangeClosed(2, i).reduce(1, (a, b) -> a * b));
	}
	
	
	@Test
	public void test2() throws InterruptedException, ExecutionException{
		double[][] distanceMatrix = Task.produceTask(Request.randomFakeSet(11)).get().getDistanceMatrix();
		System.out.println(Minimum.calculateMinimum(distanceMatrix));
	}
	
}
