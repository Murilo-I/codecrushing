package br.com.codecrushing.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.codecrushing.daos.BookDao;
import br.com.codecrushing.models.Book;

@Model
public class AdminDetailsBean {

	@Inject
	private BookDao bookDao;
	
	private Book book;
	private Integer id;
	
	public void loadDetail() {
		this.book = bookDao.findById(id);
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
