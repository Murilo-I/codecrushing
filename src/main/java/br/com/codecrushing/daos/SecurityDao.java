package br.com.codecrushing.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.codecrushing.models.SystemUser;

public class SecurityDao {

	@PersistenceContext
	private EntityManager manager;

	public SystemUser findByEmail(String email) {
		return manager.createQuery("select su from SystemUser su where su.login = :email",
				SystemUser.class).setParameter("email", email).getSingleResult();
	}
}
