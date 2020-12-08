package br.com.codecrushing.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.codecrushing.models.Purchase;

public class PurchaseDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Purchase purchase) {
		manager.persist(purchase);
	}

	public Purchase findByUUID(String uuid) {
		return manager.createQuery("select p from Purchase p where p.uuid = :uuid",
				Purchase.class).setParameter("uuid", uuid).getSingleResult();
	}
}
