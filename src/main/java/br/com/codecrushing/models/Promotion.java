package br.com.codecrushing.models;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Promotion {

	private Book book = new Book();
	private String title;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String toJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("title", title).add("id", book.getId());
		return job.build().toString();
	}
}
