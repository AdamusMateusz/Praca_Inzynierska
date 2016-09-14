package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.mateusz.komiwojazer.utils.MapOverwiew;
import com.mateusz.komiwojazer.utils.Minimum;
import com.mateusz.komiwojazer.utils.Request;

@Service
public class TravelingSalesmanService {
	private ConcurrentHashMap<Integer, CompletableFuture<Task>> tasks;
	private ConcurrentHashMap<Integer, Task> completedTasks;
	private ConcurrentHashMap<Integer, Request> arguments;
	private AtomicInteger counter;
	private ExecutorService executor;
	
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
		return startNewTask(-1,request,true);
	}
	
	public Integer startNewTask(Request request, boolean stopped) {
		return startNewTask(-1,request,stopped);
	}
	
	private Integer startNewTask(int id, Request request, boolean stopped) {
		Integer key = (id == -1 ? counter.getAndIncrement() : id);
		request.setId(key);
		arguments.put(key, request);
		CompletableFuture<Task> t = Task.produceTask(request);
		
		if(!stopped){
			tasks.put(key, t);
			
		}else{
			t.thenAccept(completedTask->completedTasks.put(key, completedTask));
		}
		return key;
	}

	public void updateRequest(int id, Request set,boolean stopped) {
		Request oldSet = arguments.get(id);
		
		if (oldSet != null)
			if (oldSet.isChangeCritical(set)){
				delete(id);
				startNewTask(id,set,stopped);
			}
			else
				arguments.put(id, set);
		else 
				arguments.put(id, set);
		
	}

	public Future<Task> getMap(int id) {
		return tasks.getOrDefault(id, CompletableFuture.completedFuture(completedTasks.get(id)));
	}

	public List<MapOverwiew> getAllMapsOverview() {

		return arguments.entrySet().parallelStream()
		.map(entry -> produceSingleOverwiew(entry.getValue(), entry.getKey()))
		.sorted((e1,e2)->Integer.compare(e1.getId(), e2.getId()))
		.collect(Collectors.toList());
		
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
		double heuristicValue = -1;
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
			complete = task.isCompleted();
			cities = task.getCities();
			heuristicValue = task.getMinimum() ;
			
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			return null;
		}
		return new MapOverwiew(r,heuristicValue, complete,running,cities,quality);
	}

	@PostConstruct
	public void construct() {

		threadFactory().newThread(() -> {

			try {
				while (true) {
					if (tasks.isEmpty()) {
						TimeUnit.SECONDS.sleep(3);
					} else {
						executeTasks();
						//TimeUnit.MILLISECONDS.sleep(50);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();

	}

	private ThreadFactory threadFactory() {
		// tworzy fabryke watkow
		return r -> {
			Thread t = new Thread(r);
			// gdy program konczy dzialanie, to watki nie powinny blokowac
			// zamykania programu
			t.setDaemon(true);
			return t;
		};
	}

	private void executeTasks() {
		tasks.entrySet().stream().forEach(entry -> {
			try {
				final CompletableFuture<Task> future = entry.getValue();

				if (future.isDone()) {
					Task task = future.get();

					if (task.isCompleted() ) {
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

	public Request getRequest(int id) {
		return arguments.get(id);
	}

	/**
	 * @param id identifier of task
	 * @return true if some task is stopped, false otherwise
	 */
	public boolean stop(int id) {
		final CompletableFuture<Task> completableFuture = tasks.remove(id);
		
		if (Objects.nonNull(completableFuture)) {
			completableFuture.thenAccept(task -> completedTasks.put(id, task));
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param id identifier of task
	 * @return true if some task is resumed, false otherwise
	 */
	public boolean resume(int id) {
		Task removed = completedTasks.remove(id);
		
		if(Objects.nonNull(removed)){
			tasks.put(id,CompletableFuture.completedFuture(removed));
			return true;
		}
		
		return false;
	}

	public boolean delete(int id) {
		
		Object ob = completedTasks.remove(id);
		
		if(Objects.isNull(ob)){
			tasks.remove(id);
		}
		
		arguments.remove(id);
		return true;
	}
	
	public boolean isRunning(int id){
		return Objects.nonNull(tasks.get(id));
	}

}
