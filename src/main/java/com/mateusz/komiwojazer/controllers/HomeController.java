package com.mateusz.komiwojazer.controllers;

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

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit() {
		return "edit";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "add";
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map() {
		return "map";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}

}
