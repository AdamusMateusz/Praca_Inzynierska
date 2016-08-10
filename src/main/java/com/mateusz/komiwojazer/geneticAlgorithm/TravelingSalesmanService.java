package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.mateusz.komiwojazer.utils.MapOverwiew;
import com.mateusz.komiwojazer.utils.Request;

@Service
public class TravelingSalesmanService {
	private ExecutorService executor;
	private ConcurrentHashMap<Integer, CompletableFuture<Task>> tasks;
	private ConcurrentHashMap<Integer, Task> completedTasks;
	private ConcurrentHashMap<Integer, Request> arguments;
	//private ConcurrentHashMap<Integer, MinAndMax> results;
	private AtomicInteger counter;

	public TravelingSalesmanService() {

		// inicjowanie nowych zmiennych
		tasks = new ConcurrentHashMap<>();
		completedTasks = new ConcurrentHashMap<>();
		arguments = new ConcurrentHashMap<>();
		counter = new AtomicInteger(0);

		// tworzenie executora ktory bedzie zarzadzal wszystkimi watkami
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), threadFactory());

	}

	public Integer startNewTask(Request request) {
		return startNewTask(-1,request);
	}
	
	private Integer startNewTask(int id, Request request) {
		Integer key = (id == -1 ? counter.getAndIncrement() : id);
		request.setId(key);
		arguments.put(key, request);
		CompletableFuture<Task> t = Task.produceTask(request);
		tasks.put(key, t);
		//t.thenAccept(task -> results.put(key, MinAndMax.getMinAndMax(task)));
		return key;
	}

	public void updateRequest(int id, Request set) {
		Request oldSet = arguments.get(id);
		if (oldSet == null)
			arguments.put(id, set);
		else {
			if (oldSet.isChangeCritical(set))
				startNewTask(set);
			else
				arguments.put(id, set);
		}
	}

	public void delete(int id, String password) {
		// TODO Auto-generated method stub

	}

	public Future<Task> getMap(int id) {
		return tasks.getOrDefault(id, CompletableFuture.completedFuture(completedTasks.get(id)));
	}

	public List<MapOverwiew> getAllMapsOverview() {
		List<MapOverwiew> maps = new ArrayList<>();

		Set<Entry<Integer, Request>> entrySet = arguments.entrySet();
		
		for (Entry<Integer, Request> entry : entrySet) {

			maps.add(produceSingleOverwiew(entry.getValue(), entry.getKey()));
			
		}
		
		maps.sort((m1,m2)-> Integer.compare(m1.getId(),m2.getId()));
		return maps;
	}
	
	public List<MapOverwiew> getMapsOverview(int id) {
		List<MapOverwiew> result= new ArrayList<>();
		
		while(++id <= counter.get() && result.size() <= 5){
			final Request request = arguments.get(id);
			if(request != null){
				result.add(produceSingleOverwiew(request, id));
			}
		}
		
		return result;
	}
	
	private MapOverwiew produceSingleOverwiew(Request r, int id){
		boolean complete = false;
		boolean running = true;
		double quality =-1;
		List<City> cities = new ArrayList<>();
		try {
			 CompletableFuture<Task> completableFuture = tasks.get(id);
			 Task task;
			 if(completableFuture != null){
				task = completableFuture.get(1, TimeUnit.SECONDS);
			}
			else{
				task = completedTasks.get(id);
				running = false;
			}
					
			quality = task.getQuality();
			complete = task.isComplete();
			cities = task.getCities();
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		return new MapOverwiew(r, complete,running,cities,quality);
	}

	@PostConstruct
	public void construct() {

		threadFactory().newThread(() -> {

			try {
				while (true) {
					if (tasks.isEmpty()) {
						TimeUnit.SECONDS.sleep(1);
					} else {
						executeTasks();
						TimeUnit.MILLISECONDS.sleep(50);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();

	}

	private void executeTasks() {
		tasks.forEachEntry(Long.MAX_VALUE, entry -> {
			try {
				final CompletableFuture<Task> future = entry.getValue();

				if (future.isDone()) {
					Task task = future.get();

					if (task.isComplete()) {
						completedTasks.put(entry.getKey(), entry.getValue().get());
						tasks.remove(entry.getKey());
					} else {		
						
						tasks.put(entry.getKey(),CompletableFuture
								.supplyAsync(() -> task.proceed(arguments.get(entry.getKey())), executor));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@PreDestroy
	public void destroy() {
		// Watki sa demonami, wiec ta funkcja nie jest niezbêdna, ale lepiej
		// posprz¹taæ po programie
		try {
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		} finally {
			if (!executor.isTerminated()) {
			}
			executor.shutdownNow();
		}
	}

	private ThreadFactory threadFactory() {
		// tworzy fabryke fatkow
		return r -> {
			Thread t = new Thread(r);
			// gdy program konczy dzialanie, to watki nie powinny blokowac
			// zamykania programu
			t.setDaemon(true);
			return t;
		};
	}

	public Request getRequest(int id) {
		return arguments.get(id);
	}

}
