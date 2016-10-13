package com.mateusz.komiwojazer.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@Autowired
	private MessageSendingOperations<String> messages;
	
	@RequestMapping(value="/topics",  method = RequestMethod.GET)
	public @ResponseBody List<Topic> topics(){
		return chat.getTopics();
	}
	
	@RequestMapping(value="/messages/{id}",  method = RequestMethod.GET)
	public @ResponseBody List<Message> messages(@PathVariable("id") int id){
		return chat.getMessages(id);
	}
	
	@RequestMapping(value="/addTopic",  method = RequestMethod.POST, headers="Accept=*/*")
	public void addTopic(@RequestBody String topic){
		chat.addTopic(topic);
		messages.convertAndSend("/topic/topics", topic);
	}
	
	@RequestMapping(value="/addMessage/{topicId}",  method = RequestMethod.POST, headers="Accept=*/*")
	public void addMessage(@PathVariable("topicId") int topicId, @RequestBody Message m){
		chat.addMessage(topicId, m);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("topicID", topicId);
		messages.convertAndSend("/topic/messages", m, map);
	}
	
	@MessageMapping("/chatTopics")
	@SendTo("/topic/topics")
	public Topic sendTopic(Topic topic) throws Exception {
		return topic;
	}
	
	@MessageMapping("/chatMessages")
	@SendTo("/topic/messages")
	public Message sendMessage(Message message) throws Exception {
		return message;
	}
	
	public void setChat(ChatService chat){
		this.chat = chat;
	}

	public void setMessages(MessageSendingOperations<String> messages){
		this.messages = messages;
	}
	
}
