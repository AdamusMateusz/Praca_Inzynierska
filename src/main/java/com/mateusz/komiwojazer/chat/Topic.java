package com.mateusz.komiwojazer.chat;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Topic {
	private final String title;
	private final List<Message> messages;


	public Topic(String title) {
		this.title = title;
		messages = new ArrayList<Message>();

	}

	public String getTitle() {
		return title;
	}

	public void addMessage(Message m) {
		messages.add(m);
	}
	
	public void addMessages(List<Message> m) {
		messages.addAll(m);
	}

	@JsonIgnore
	public List<Message> getMessages() {
		return messages;
	}

	public int getMessagesCount() {
		return messages.size();
	}

}
