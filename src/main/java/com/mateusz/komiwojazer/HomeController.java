package com.mateusz.komiwojazer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String start() {
		return "start";
	}
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String stats() {
		return "stats";
	}
	
	@RequestMapping(value = "/maps", method = RequestMethod.GET)
	public String maps() {
		return "maps";
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chat() {
		return "chat";
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info() {
		return "info";
	}
	
	@RequestMapping(value = "/boot", method = RequestMethod.GET)
	public String boot() {
		return "boot";
	}
	
}
