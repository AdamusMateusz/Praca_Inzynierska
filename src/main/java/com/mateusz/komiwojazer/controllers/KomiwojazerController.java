package com.mateusz.komiwojazer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mateusz.komiwojazer.geneticAlgorithm.ArgumentsSet;
import com.mateusz.komiwojazer.geneticAlgorithm.GeneticAlgorithmService;
import com.mateusz.komiwojazer.geneticAlgorithm.Map;

@RestController
@RequestMapping(value = "/komiwojazer")
public class KomiwojazerController {

	@Autowired
	private GeneticAlgorithmService service;
	
	@RequestMapping("/test")
	public String test(){
		return "test";
	}
	
	@RequestMapping(value="/startMap", method = RequestMethod.POST)
	public Integer startNewMap(@RequestBody ArgumentsSet set){
		return service.startNewMap(set);
	}
	
	@RequestMapping(value="/changeArguments", method = RequestMethod.PUT)
	public void updateArguments(@RequestBody ArgumentsSet set){
		service.updateArguments(set);
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public Map getMap(@PathVariable("id") int id){
		return service.getMap();
	}
	
	@RequestMapping(value="/getMapsOverview/{lastID}/{quantity}", method = RequestMethod.GET)
	public List<Map> getMapsOverview(@PathVariable("id") int id, @PathVariable(value="quantity") int quantity){
		return service.getMapsOverview(id,quantity);
	}
	
	@RequestMapping(value="/deleteMap/{id}", method = RequestMethod.DELETE)
	public void getMapsOverview(@PathVariable("id") int id,@RequestBody String password){
		service.delete(id,password);
	}
	
	public void setService(GeneticAlgorithmService service) {
		this.service = service;
	}
}
