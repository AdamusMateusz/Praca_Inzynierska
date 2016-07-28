package com.mateusz.komiwojazer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mateusz.komiwojazer.geneticAlgorithm.MinAndMax;

public class MinMaxTest {

	@Test
	public void isNotValid(){
		int[] cities = {1,1,1,1,1,1};
		assertEquals(false, MinAndMax.isValidRoute(cities));
	}
	
	@Test
	public void isValid(){
		int[] cities = {5,2,6,4,0,3,1};
		assertEquals(true, MinAndMax.isValidRoute(cities));
	}
	
	@Test
	public void isLast(){
		int[] cities = {6,5,4,3,2,1,0};
		int[] cities2 = {5,4,3,2,1,0};
		assertEquals(true, MinAndMax.isLastElement(cities));
		assertEquals(true, MinAndMax.isLastElement(cities2));
	}
	
	@Test
	public void nextElement(){
		int[] cities = {4,5,3,3,1,5};
		int[] expected = {4,5,3,3,2,0};
		MinAndMax.setNextElement(cities);
		assertArrayEquals(expected, cities);
	}
	
	
}
