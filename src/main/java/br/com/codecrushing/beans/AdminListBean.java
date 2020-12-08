package br.com.codecrushing.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.codecrushing.daos.BookDao;
import br.com.codecrushing.models.Book;

@Model
public class AdminListBean {
	
	@Inject
	private BookDao dao;
	
	public List<Book> getBooks() {
		return dao.listBooks();
	}
}
