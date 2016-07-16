package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class GeneticAlgorithmService {

	private ConcurrentHashMap<Integer,Future<Map>> tasks;
	private ConcurrentHashMap<Integer,ArgumentsSet> arguments;
	private AtomicInteger counter;
	
	public GeneticAlgorithmService(){
		tasks = new ConcurrentHashMap<>();
		arguments = new ConcurrentHashMap<>();
		counter = new AtomicInteger(0);
	}
	
	public Integer startNewMap(ArgumentsSet set) {
		
		Integer i = counter.getAndIncrement();
		Map m = new Map();
		//Completable future
		
		return i;
	}

	public void updateArguments(ArgumentsSet set) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id, String password) {
		// TODO Auto-generated method stub
		
	}

	public Map getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map> getMapsOverview(int id, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
