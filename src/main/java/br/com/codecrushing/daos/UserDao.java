package br.com.codecrushing.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.codecrushing.models.User;

public class UserDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(User user) {
		manager.persist(user);
	}
}
