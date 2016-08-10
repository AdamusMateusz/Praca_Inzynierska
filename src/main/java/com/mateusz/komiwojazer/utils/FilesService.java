package com.mateusz.komiwojazer.utils;

public class FilesService {

	private static FilesService service;
	
	private FilesService() {
	}
	
	private static FilesService getInstance(){
		if(service == null)
			service = new FilesService();
		return service;
	}
	
	public static void save(int id, double quality) {
		//TODO
	}
	

}
