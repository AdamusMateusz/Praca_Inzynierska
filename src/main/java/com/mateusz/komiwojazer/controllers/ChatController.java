package com.mateusz.komiwojazer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mateusz.komiwojazer.chat.ChatService;
import com.mateusz.komiwojazer.chat.Message;
import com.mateusz.komiwojazer.chat.Topic;

@RestController
@RequestMapping(value="/chat")
public class ChatController {

	@Autowired
	private ChatService chat;
	
	@RequestMapping(value="/topics",  method = RequestMethod.GET)
	public @ResponseBody List<Topic> topics(){
		return chat.getTopics();
	}
	
	@RequestMapping(value="/messages{id}",  method = RequestMethod.GET)
	public @ResponseBody List<Message> messages(@RequestParam int id){
		return chat.getMessages(id);
	}
	
	@RequestMapping(value="/addTopic",  method = RequestMethod.POST,headers="Accept=*/*")
	public void addTopic(@RequestBody String topic){
		chat.addTopic(topic);
	}
	
	@RequestMapping(value="/addMessage{topicId}",  method = RequestMethod.POST,headers="Accept=*/*")
	public void addMessage(@RequestParam int topicId, @RequestBody Message m){
		chat.addMessage(topicId, m);
	}
	
	public void setChat(ChatService chat){
		this.chat = chat;
	}
}
