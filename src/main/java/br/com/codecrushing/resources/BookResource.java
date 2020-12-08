package br.com.codecrushing.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.codecrushing.daos.BookDao;
import br.com.codecrushing.models.Book;

@Path("books")
public class BookResource {

	@Inject
	private BookDao dao;
	
	@GET
	@Path("releases")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	@Wrapped(element = "books")
	public List<Book> latestReleasesInJson() {
		return dao.latestReleases();
	}
}
