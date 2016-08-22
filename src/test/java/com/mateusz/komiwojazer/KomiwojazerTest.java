package com.mateusz.komiwojazer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.mateusz.komiwojazer.geneticAlgorithm.Minimum;
import com.mateusz.komiwojazer.geneticAlgorithm.Route;
import com.mateusz.komiwojazer.geneticAlgorithm.Task;
import com.mateusz.komiwojazer.utils.Request;

public class KomiwojazerTest {

	@Test
	public void randomRoute() {
		Route r = Route.generateRandomRoute(5);
		assertEquals(5, r.getCities().length);
	}

	@Test
	public void sort() throws Exception {
		List<Route> routes = new ArrayList<>();
		Route r = new Route(new int[1]);
		
		routes.add(r.withRate(100));
		routes.add(r.withRate(20));
		routes.add(r.withRate(1));
		routes.add(r.withRate(0));
		routes.add(r.withRate(-1));
		routes.add(r.withRate(-1));
		routes.add(r.withRate(123));
		routes.add(r.withRate(70));
		routes.forEach(ro->System.out.println(ro.getQuality()));
		System.out.println("---");
		Route.sortRoutes(routes).forEach(ro->System.out.println(ro.getQuality()));
	}
	
	@Test
	public void testName() throws Exception {
	
		double d;
		double max =0;
		do{
			d = ThreadLocalRandom.current().nextDouble(0,10);
			if(d >= max){
				max = d;
				System.out.println(max);
			}
			
		}while(d <= 9.9);
	}
}
