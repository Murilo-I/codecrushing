package br.com.codecrushing.beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.codecrushing.daos.AuthorDao;
import br.com.codecrushing.daos.BookDao;
import br.com.codecrushing.infra.FileSaver;
import br.com.codecrushing.models.Author;
import br.com.codecrushing.models.Book;

@Named
@RequestScoped
public class AdminBooksBean {

	private Book book = new Book();
	
	@Inject
	private FacesContext context; 
	@Inject
	private BookDao bookDao;
	@Inject
	private AuthorDao authorDao;
	
	private Part bookCover;
	
	@Transactional
	public String save() throws IOException {
		this.bookDao.save(this.book);
		
		String path = new FileSaver().write(this.bookCover);
		this.book.setCoverPath(path);
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso"));
		
		return "/books/bookList?faces-redirect=true";
	}
	
	public List<Author> getAuthors() {
		return authorDao.listAuthors();
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Part getBookCover() {
		return bookCover;
	}
	public void setBookCover(Part bookCover) {
		this.bookCover = bookCover;
	}
}
