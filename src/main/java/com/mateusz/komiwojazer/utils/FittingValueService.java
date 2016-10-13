package com.mateusz.komiwojazer.utils;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FittingValueService {

	private static ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Double>>  map;
	
	static{
		map = new ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Double>>();
	}
	
	public static void save(int id, double quality) {
		map.get(id).add(quality);
	}

	public static void saveFirst(int id, double quality) {
		ConcurrentLinkedQueue<Double> list = new ConcurrentLinkedQueue<Double>();
		list.add(quality);
		map.put(id, list);
	}
	
	public static void deleteMap(int id){
		map.remove(id);
	}
	
	public static Queue<Double> getValues(int id){

		return map.get(id);
	}
	

}
