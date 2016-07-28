package com.mateusz.komiwojazer;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.mateusz.komiwojazer.geneticAlgorithm.ArgumentsSet;
import com.mateusz.komiwojazer.geneticAlgorithm.MinAndMax;
import com.mateusz.komiwojazer.geneticAlgorithm.Route;
import com.mateusz.komiwojazer.geneticAlgorithm.Task;

public class KomiwojazerTest {

	public void minMax() {
		try {
			Task t = Task.produceTask(new ArgumentsSet(8, 5, 5, 50, 50, 5, 5, (byte) 75, true, ""))
					.get(10,TimeUnit.SECONDS);
			MinAndMax mam = MinAndMax.getMinAndMax(t);

			System.out.println(mam);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void randomRoute() {
		Route r = Route.generateRandomRoute(5);
		assertEquals(5, r.getCities().length);
	}

	@Test
	public void CPUusage() {
		List<CompletableFuture<MinAndMax>> list = Stream
				.generate(() -> Task.produceTask(new ArgumentsSet(9, 5, 5, 50, 50, 5, 5, (byte) 75, true, "")))
				.limit(Runtime.getRuntime().availableProcessors()-1)
				.map(t -> t.thenApply(task -> MinAndMax.getMinAndMax(task))).collect(Collectors.toList());

		list.stream().map(m -> {
			try {
				return m.get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).forEach(m -> System.out.println(m));
	}

}
