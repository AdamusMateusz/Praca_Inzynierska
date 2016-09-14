package com.mateusz.komiwojazer.utils;

public class FittingValueService {

	private static FittingValueService service;
	//private static map
	
	private FittingValueService() {
	}
	
	private static FittingValueService getInstance(){
		if(service == null)
			service = new FittingValueService();
		return service;
	}
	
	public static void save(int id, double quality) {
		//TODO dodac zapisywanie
	}

	public static void saveFirst(int id, double quality) {
		// TODO Auto-generated method stub
		
	}
	

}
