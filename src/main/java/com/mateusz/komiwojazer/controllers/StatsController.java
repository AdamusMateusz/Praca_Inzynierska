package com.mateusz.komiwojazer.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusz.komiwojazer.chat.ChatService;

@RestController
public class StatsController {

	@Autowired
	private ChatService chat;

	@RequestMapping("/stats")
	public Map<String,String> chat(){
		return null;	
	}
	
	
	public void setChat(ChatService chat) {
		this.chat = chat;
	}
	
}
