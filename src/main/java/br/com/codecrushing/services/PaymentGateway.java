package br.com.codecrushing.services;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.codecrushing.models.Payment;

public class PaymentGateway implements Serializable {

	private static final long serialVersionUID = 1L;

	public String pay(BigDecimal total) {
		Payment pay = new Payment(total);
		Entity<Payment> json = Entity.json(pay);
		
		String target = "http://book-payment.herokuapp.com/payment";
		
		Client client = ClientBuilder.newClient();
		return client.target(target).request().post(json, String.class);
	}
}
