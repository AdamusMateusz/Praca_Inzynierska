package com.mateusz.komiwojazer.chat;

public class Message {

	private final String autor;
	private final String message;

	public Message(String autor, String message) {
		this.message = message;
		this.autor = autor;
	}

	public String getAutor() {
		return autor;
	}

	public String getMessage() {
		return message;
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
