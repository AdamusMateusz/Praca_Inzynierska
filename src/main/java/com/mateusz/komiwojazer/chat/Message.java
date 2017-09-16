package com.mateusz.komiwojazer.chat;

import java.io.Serializable;

public class Message implements Serializable{

	private String author;
	private String message;

	@Deprecated
	public Message() {
	}

	public Message(String author, String message) {
		this.message = message;
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public String getMessage() {
		return message;
	}

	@Deprecated
	public void setAuthor(String author) {
		this.author = author;
	}
	@Deprecated
	public void setMessage(String message) {
		this.message = message;
	}

	public static Message getFakeMessage() {
		final String fakeAuthors[] = { "Mirek", "Antoni", "Ewa", "Roman", "Tomasz", "Artur", "Mateusz", "Mi³osz",
				"Remigiusz", "Micha³", "Alojzy", "Adrian", "Jakub", "Kamil", "Rafa³", "Kasia" };
		final String fakeMessages[] = { "Ciekawa strona", "Czeœæ", "Witam", "Witam na stronie",
				"Pozdrawiam u¿ytkowników", "Moja strona nie dzia³a poprawnie", "Nie wiem jak dodaæ now¹ wiadomoœæ",
				"Podoba mi siê kolorystyka strony", "Czegoœ mi tu brakuje", "Ile zajmuje zrobienie takiej strony",
				"Kto chce mi zrobiæ poobn¹ stronê? Zap³acê.", "Pozdrawiam u¿ytkowników", "Komiwoja¿er jest najlepszy!",
				"Szkoda ¿e mo¿na pisaæ, to ludzie bêd¹ dodawaæ dziwne rzeczy", "Autor nie zna siê na temacie",
				"Ja bym to zrobi³ lepiej", "Kiepska jest ta strona" };

		return new Message(fakeAuthors[(int) (Math.random() * fakeAuthors.length)],
				fakeMessages[(int) (Math.random() * fakeMessages.length)]);
	}
}
