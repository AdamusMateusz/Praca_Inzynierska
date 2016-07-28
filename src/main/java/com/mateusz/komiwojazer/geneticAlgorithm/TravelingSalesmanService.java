package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class TravelingSalesmanService {
	private ExecutorService executor;
	private ConcurrentHashMap<Integer, CompletableFuture<Task>> tasks;
	private ConcurrentHashMap<Integer, Task> completedTasks;
	private ConcurrentHashMap<Integer, ArgumentsSet> arguments;
	private ConcurrentHashMap<Integer, MinAndMax> results;
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

	public Integer startNewTask(ArgumentsSet set) {
		return startNewTask(-1,set);
	}
	
	private Integer startNewTask(int id, ArgumentsSet set) {
		Integer key = (id == -1 ? counter.getAndIncrement() : id);
		arguments.put(key, set);
		CompletableFuture<Task> t = Task.produceTask(set);
		tasks.put(key, t);
		t.thenAccept(task -> results.put(key, MinAndMax.getMinAndMax(task)));
		return key;
	}

	public void updateArguments(int id, ArgumentsSet set) {
		ArgumentsSet oldSet = arguments.get(id);
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

	public List<Task> getMapsOverview(int id, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostConstruct
	public void construct() {

		threadFactory().newThread(() -> {
			// TODO zrobic cos, zeby ten watek ciagle nie krecil sie bez sensu
			// na pelnych obrotach

			while (true) {
				if (tasks.isEmpty()) {
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					executeTasks();
				}
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
						tasks.put(entry.getKey(), CompletableFuture
								.supplyAsync(() -> task.proceed(arguments.get(entry.getKey())), executor));
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@PreDestroy
	public void destroy() {
		// Watki sa demonami, wiec ta funkcja nie jest potrzebna, ale lepiej
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

	public List<Task> getAllMapsOverview() {
		// TODO Auto-generated method stub
		return null;
	}
}
