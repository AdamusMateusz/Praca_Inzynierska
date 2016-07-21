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
		Integer i = counter.getAndIncrement();
		Task m = Task.produceTask(set);
		// TODO Completable future

		return i;
	}

	public void updateArguments(ArgumentsSet set) {
		// TODO Auto-generated method stub

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
				if(tasks.isEmpty()){
					try {
						TimeUnit.SECONDS.sleep(15);
						System.out.println("Pusto");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					executeTasks();
				}
			}
		}).start();

		// CompletableFuture.runAsync(() -> executeTasks(),
		// Executors.newSingleThreadExecutor(produceThreadFactory()))
		// .thenAccept(VOID -> construct());
	}

	private void executeTasks() {
		tasks.forEachEntry(Runtime.getRuntime().availableProcessors() * 2, entry -> {
			try {
				final CompletableFuture<Task> val = entry.getValue();

				if (val.isDone()) {
					Task task;
					task = val.get();

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
}
