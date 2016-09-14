package com.mateusz.komiwojazer;

import java.util.concurrent.ExecutionException;
import java.util.stream.LongStream;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.util.SocketUtils;

import com.mateusz.komiwojazer.geneticAlgorithm.Task;
import com.mateusz.komiwojazer.utils.Minimum;
import com.mateusz.komiwojazer.utils.Request;

public class MinMaxTest {

//	@Test
	public void test1(){
	for(int i = 0 ; i <= 20 ; i++)
	System.out.format("factorialValues[%d] = %dL;%n", i,LongStream.rangeClosed(2, i).reduce(1, (a, b) -> a * b));
	}
	
	
//	@Test
	public void test2() throws InterruptedException, ExecutionException{
		System.out.println(Task.produceTask(Request.randomFakeSet(10)).get().getMinimum());
		//dla 13 -> 1237,165s ~> 21min
	}
	
	@Test
	public void testMin(){
		double matrix [][] = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
		System.out.println(Minimum.calculateMinimum(matrix));
	}

}
