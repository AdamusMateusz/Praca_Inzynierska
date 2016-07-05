package com.mateusz.komiwojazer.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final List<Topic> topics;

	public ChatService() {
		topics = new ArrayList<Topic>();
	}

	public List<Topic> getTopics() {
		return topics;
	}
	
	public List<Message> getMessages(int id){
		return topics.get(id).getMessages();
	}

	@PostConstruct
	public void generateFakeTopics() {
		final String fakeTitles[] = { "O stronie", "Algorytm", "Problemy", "Rozmowy ogólne", "Przywitaj siê",
				"O wszystkim","Propozycje zmian"};


		for (int i = 0; i < fakeTitles.length; i++) {
			Topic t = new Topic(fakeTitles[i]);
			final int range = (int) (Math.random() * 15);
			t.addMessages(Stream.generate(Message::getFakeMessage).limit(range).parallel()
					.collect(Collectors.toList()));
			topics.add(t);
		}
	}
}