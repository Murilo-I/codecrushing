package br.com.codecrushing.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.codecrushing.daos.BookDao;
import br.com.codecrushing.models.Book;
import br.com.codecrushing.models.Cart;
import br.com.codecrushing.models.Item;

@Model
public class AdminCartBean {

	@Inject
	private BookDao bookDao;
	@Inject
	private Cart cart;
	
	public String add(Integer id) {
		Book book = bookDao.findById(id);
		Item item = new Item(book);
		cart.add(item);
		
		return "cart?faces-redirect=true";
	}
	
	public List<Item> getItens() {
		return cart.getItens();
	}
	
	public void remove(Item item) {
		cart.remove(item);
	}
}
