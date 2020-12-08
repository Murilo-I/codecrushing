package br.com.codecrushing.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.codecrushing.daos.PurchaseDao;

@Named	
@SessionScoped
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Set<Item> itens = new HashSet<>();
	
	@Inject
	private PurchaseDao purchaseDao;

	public void add(Item item) {
		itens.add(item);
	}

	public List<Item> getItens() {
		return new ArrayList<Item>(itens);
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (Item item : itens) {
			total = total.add(item.getBook().getPrice().multiply(new BigDecimal(item.getQuant())));
		}
		return total;
	}
	
	public BigDecimal getTotal(Item item) {
		return item.getBook().getPrice().multiply(new BigDecimal(item.getQuant()));
	}

	public void remove(Item item) {
		this.itens.remove(item);
	}
	
	public Integer getTotalQuant() {
		return itens.stream().mapToInt(item -> item.getQuant()).sum();
	}

	public void finalize(Purchase purchase) {
		purchase.setItens(toJson());
		purchase.setTotal(getTotal());
		purchaseDao.save(purchase);
	}
	
	private String toJson() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (Item item : itens) {
			builder.add(Json.createObjectBuilder().add("id", item.getBook().getId())
					.add("title", item.getBook().getTitle())
					.add("price", item.getBook().getPrice())
					.add("quantity", item.getQuant())
					.add("total", getTotal(item)));
		}
		return builder.build().toString();
	}
}
