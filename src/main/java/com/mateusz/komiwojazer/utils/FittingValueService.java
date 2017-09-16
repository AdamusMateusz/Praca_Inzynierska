package com.mateusz.komiwojazer.utils;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class FittingValueService {

	private static ConcurrentHashMap<Integer,Map<Double,Integer>>  map;
	
	static{
		map = new ConcurrentHashMap<Integer, Map<Double,Integer>>();
	}
	
	public static void save(int id, double quality) {
		map.get(id)
		.compute(quality, (k,v)-> v == null ? Integer.valueOf(1):Integer.sum(v, 1));
	}

	public static void saveFirst(int id, double quality) {
		Map<Double,Integer> list = new TreeMap<Double,Integer>();
		list.put(quality, Integer.valueOf(1));
		map.put(id, list);
	}
	
	public static void deleteMap(int id){
		map.remove(id);
	}
	
	public static Map<Double,Integer> getValues(int id){
		return map.get(id);
	}
	

}
