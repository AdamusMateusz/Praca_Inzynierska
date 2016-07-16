package com.mateusz.komiwojazer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusz.komiwojazer.chat.ChatService;

@RestController
@RequestMapping(value="/stats")
public class StatsController {

	@Autowired
	private ChatService chat;

	@RequestMapping("/topicsNumber")
	public int topicsNumber(){
	return chat.getTopicsNumber();	
	}
	
	
	public void setChat(ChatService chat) {
		this.chat = chat;
	}
	
}
