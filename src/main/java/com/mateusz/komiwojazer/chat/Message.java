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
		final String fakeAuthors[] = { "Mirek", "Antoni", "Ewa", "Roman", "Tomasz", "Artur", "Mateusz", "Mi�osz",
				"Remigiusz", "Micha�", "Alojzy", "Adrian", "Jakub", "Kamil", "Rafa�", "Kasia" };
		final String fakeMessages[] = { "Ciekawa strona", "Cze��", "Witam", "Witam na stronie",
				"Pozdrawiam u�ytkownik�w", "Moja strona nie dzia�a poprawnie", "Nie wiem jak doda� now� wiadomo��",
				"Podoba mi si� kolorystyka strony", "Czego� mi tu brakuje", "Ile zajmuje zrobienie takiej strony",
				"Kto chce mi zrobi� poobn� stron�? Zap�ac�.", "Pozdrawiam u�ytkownik�w", "Komiwoja�er jest najlepszy!",
				"Szkoda �e mo�na pisa�, to ludzie b�d� dodawa� dziwne rzeczy", "Autor nie zna si� na temacie",
				"Ja bym to zrobi� lepiej", "Kiepska jest ta strona" };

		return new Message(fakeAuthors[(int) (Math.random() * fakeAuthors.length)],
				fakeMessages[(int) (Math.random() * fakeMessages.length)]);
	}
}
