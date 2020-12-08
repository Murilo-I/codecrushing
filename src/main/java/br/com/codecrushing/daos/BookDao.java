package br.com.codecrushing.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;

import br.com.codecrushing.models.Book;

@Stateful
public class BookDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	public void save(Book book) {
		manager.persist(book);
	}
	
	public List<Book> listBooks() {
		return manager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class)
				.getResultList();
	}

	public List<Book> latestReleases() {
		String jpql = "select b from Book b order by b.id desc";
	    return manager.createQuery(jpql, Book.class)
	            .setMaxResults(5)
	            .setHint(QueryHints.HINT_CACHEABLE, true)
	            .setHint(QueryHints.HINT_CACHE_REGION, "home")
	            .getResultList();
	}

	public List<Book> allBooks() {
		String jpql = "select b from Book b order by b.id desc";
        return manager.createQuery(jpql, Book.class)
        		.setFirstResult(5)
        		.setHint(QueryHints.HINT_CACHEABLE, true)
        		.setHint(QueryHints.HINT_CACHE_REGION, "home")
                .getResultList();
	}

	public Book findById(Integer id) {
		return manager.find(Book.class, id);
		
		/**
		 * return manager.createQuery("select b from Book b join fetch b.authors where b.id = :id",
				Book.class).setParameter("id", id).getSingleResult();
		 */
	}
	
	public void cleanCache() {
		//JPA
		Cache cache = manager.getEntityManagerFactory().getCache();
		cache.evict(Book.class); //pode passar o id de um objeto específico também
		cache.evictAll();
		
		//HIBERNATE 
		SessionFactory factory = manager.getEntityManagerFactory().unwrap(SessionFactory.class);
		factory.getCache().evictAllRegions();
		factory.getCache().evictQueryRegion("home");
	}
}
