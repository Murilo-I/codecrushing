package br.com.codecrushing.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.codecrushing.daos.BookDao;
import br.com.codecrushing.models.Book;

@Model
public class AdminHomeBean {

	@Inject
	private BookDao bookDao;
	
	public List<Book> latestReleases() {
		return bookDao.latestReleases();
	}
	
	public List<Book> allBooks() {
		return bookDao.allBooks();
	}
}
