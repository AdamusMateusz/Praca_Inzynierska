package com.mateusz.komiwojazer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mateusz.komiwojazer.chat.ChatService;
import com.mateusz.komiwojazer.chat.Message;
import com.mateusz.komiwojazer.chat.Topic;

@RestController
public class ChatController {

	@Autowired
	private ChatService chat;
	
	@RequestMapping(value="/chat/topics",  method = RequestMethod.GET)
	public @ResponseBody List<Topic> message(){
		return chat.getTopics();
	}
	
	@RequestMapping(value="/chat/messages{id}",  method = RequestMethod.GET)
	public @ResponseBody List<Message> messages(@RequestParam int id){
		return chat.getMessages(id);
	}
	
	
	public void setChat(ChatService chat){
		this.chat = chat;
	}
}
