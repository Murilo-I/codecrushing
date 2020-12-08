package br.com.codecrushing.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.codecrushing.models.Author;

public class AuthorDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Author> listAuthors() {
		return manager.createQuery("select a from Author a", Author.class)
				.getResultList();
	}

	
}
