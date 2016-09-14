package com.mateusz.komiwojazer.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mateusz.komiwojazer.geneticAlgorithm.Task;
import com.mateusz.komiwojazer.geneticAlgorithm.TravelingSalesmanService;
import com.mateusz.komiwojazer.utils.MapOverwiew;
import com.mateusz.komiwojazer.utils.Request;

@RestController
@RequestMapping(value = "/komiwojazer")
public class KomiwojazerController {

	private static final int AWAIT_TIME = 10;

	@Autowired
	private TravelingSalesmanService service;

	@RequestMapping(value = "/startMap", method = RequestMethod.POST)
	public @ResponseBody Integer startNewMap(@RequestBody Request set,@RequestParam("stopped")boolean stopped) {
		return service.startNewTask(set,stopped);
	}

	@RequestMapping(value = "/changeRequest/{id}", method = RequestMethod.PUT)
	public void updateRequest(@RequestBody Request set, @PathVariable("id") int id,@RequestParam("stopped")boolean stopped) {
		set.setId(id);
		service.updateRequest(id, set,stopped);
	}
	
	@RequestMapping(value = "/getRequest/{id}", method = RequestMethod.GET)
	public @ResponseBody Request getRequest(@PathVariable("id") int id) {
		return service.getRequest(id);
	}
	
	@RequestMapping(value = "/stopMap/{id}", method = RequestMethod.PATCH)
	public boolean stopMap(@PathVariable("id") int id) {
		return service.stop(id);
	}
	
	@RequestMapping(value = "/resumeMap/{id}", method = RequestMethod.PATCH)
	public boolean resumeMap(@PathVariable("id") int id) {
		return service.resume(id);
	}
	
	@RequestMapping(value = "/isRunningMap/{id}", method = RequestMethod.GET)
	public boolean isRunning(@PathVariable("id") int id) {
		return service.isRunning(id);
	}
	
	@RequestMapping(value = "/getFittingFunctionValues/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Double> getFittingFunctionValues(@PathVariable("id") int id) {
		List<Double> values = new ArrayList<>();
		
		for(int i = ThreadLocalRandom.current().nextInt(100,201); i >= 0; i--){
			values.add(new Double(-1));
		}
		
		double max = ThreadLocalRandom.current().nextDouble(2000.0, 30000.0);
		
		while (max > 800){
			
			for(int i = ThreadLocalRandom.current().nextInt(5,20); i >=0;i--)
				values.add(new Double(max/1000.0));
			
			
			max -= ThreadLocalRandom.current().nextDouble(50,300);
		}
		
		return values;
	}
	

	@RequestMapping(value = "/getMap/{id}", method = RequestMethod.GET)
	public @ResponseBody Task getMap(@PathVariable("id") int id)
			throws InterruptedException, ExecutionException, TimeoutException {
		return service.getMap(id).get(AWAIT_TIME, TimeUnit.SECONDS);
	}

	@RequestMapping(value = "/getMapsOverview/{id}", method = RequestMethod.GET)
	public @ResponseBody List<MapOverwiew> getMapsOverview(@PathVariable("id") int id) {
		return service.getMapsOverview(id);
	}

	@RequestMapping(value = "/getAllMapsOverview", method = RequestMethod.GET)
	public @ResponseBody List<MapOverwiew> getAllMapsOverview() {
		return service.getAllMapsOverview();
	}

	@RequestMapping(value = "/deleteMap/{id}", method = RequestMethod.DELETE)
	public void deleteMap(@PathVariable("id") int id) {
		service.delete(id);
	}

	public void setService(TravelingSalesmanService service) {
		this.service = service;
	}

	@ExceptionHandler(TimeoutException.class)
	public @ResponseBody String timeout(HttpServletResponse rs,Exception ex) {
		ex.printStackTrace();
		rs.setStatus(504);
		return "timeout".toUpperCase();
	}

	@ExceptionHandler(ExecutionException.class)
	public @ResponseBody String executionException(HttpServletResponse rs,Exception ex) {
		ex.printStackTrace();
		rs.setStatus(500);
		return "execution_exception".toUpperCase();
	}

	@ExceptionHandler(InterruptedException.class)
	public @ResponseBody String interruptedException(HttpServletResponse rs,Exception ex) {
		ex.printStackTrace();
		rs.setStatus(500);
		return "interrupted_exception".toUpperCase();
	}

	@ExceptionHandler(NullPointerException.class)
	public @ResponseBody String nullPointer(HttpServletResponse rs,Exception ex) {
		ex.printStackTrace();
		rs.setStatus(500);
		return "nullPointer_exception".toUpperCase();
	}
	
//	@PostConstruct
	public void constructFakeMaps(){
		for (int i=5; i <= 50; i+=5)
			service.startNewTask(Request.randomFakeSet(i));
		
	}
}
