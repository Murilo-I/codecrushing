package br.com.codecrushing.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Purchase {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;
	@Lob
	private String itens;
	
	private String uuid;
	
	private BigDecimal total;
	
	@PrePersist
	public void creatUUId() {
		this.uuid = UUID.randomUUID().toString();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getItens() {
		return itens;
	}
	public void setItens(String itens) {
		this.itens = itens;
	}
	public String getUUID() {
		return uuid;
	}
	public void setUUId(String UUId) {
		uuid = UUId;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
