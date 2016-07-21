package com.mateusz.komiwojazer.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mateusz.komiwojazer.geneticAlgorithm.ArgumentsSet;
import com.mateusz.komiwojazer.geneticAlgorithm.Task;
import com.mateusz.komiwojazer.geneticAlgorithm.TravelingSalesmanService;

@RestController
@RequestMapping(value = "/komiwojazer")
public class KomiwojazerController {

	@Autowired
	private TravelingSalesmanService service;
	
	@RequestMapping("/test")
	public @ResponseBody String test(){
		return "test";
	}
	
	@RequestMapping(value="/startMap", method = RequestMethod.POST)
	public @ResponseBody Integer startNewMap(@RequestBody ArgumentsSet set){
		return service.startNewTask(set);
	}
	
	@RequestMapping(value="/changeArguments", method = RequestMethod.PUT)
	public void updateArguments(@RequestBody ArgumentsSet set){
		service.updateArguments(set);
	}
	
	@RequestMapping(value="/getMap/{id}", method = RequestMethod.GET)
	public @ResponseBody Task getMap(@PathVariable("id") int id) throws InterruptedException, ExecutionException, TimeoutException{
		return service.getMap(id).get(10, TimeUnit.SECONDS);
	}
	
	@RequestMapping(value="/getMapsOverview/{lastID}/{quantity}", method = RequestMethod.GET)
	public @ResponseBody List<Task> getMapsOverview(@PathVariable("id") int id, @PathVariable(value="quantity") int quantity){
		return service.getMapsOverview(id,quantity);
	}
	
	@RequestMapping(value="/deleteMap/{id}", method = RequestMethod.DELETE)
	public void getMapsOverview(@PathVariable("id") int id,@RequestBody String password){
		service.delete(id,password);
	}
	
	public void setService(TravelingSalesmanService service) {
		this.service = service;
	}
	
	//TODO Ustawic prawidlowe statusy (np.404) do odpowiadajacego bledu
	@ExceptionHandler(TimeoutException.class)
	public @ResponseBody String timeout(HttpServletResponse rs){
		return "timeout";
	}
	
	@ExceptionHandler(ExecutionException.class)
	public @ResponseBody String executionException(HttpServletResponse rs){
		return "executionException";
	}
	
	@ExceptionHandler(InterruptedException.class)
	public @ResponseBody String interruptedException(HttpServletResponse rs){
		return "interruptedException";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public @ResponseBody String nullPointer(HttpServletResponse rs){
		rs.setStatus(200);
		return "nullPointerException";
	}
}
