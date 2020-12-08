package br.com.codecrushing.services;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.codecrushing.daos.PurchaseDao;
import br.com.codecrushing.models.Purchase;

@Path("/payment")
public class PaymentService {

	@Inject
	private PaymentGateway pg;
	@Inject
	private PurchaseDao dao;
	@Inject
	private JMSContext jmsContext;
	@Resource(name="java:/jms/topics/PurchaseCarTopic")
	private Destination dest;

	private static ExecutorService executor = Executors.newFixedThreadPool(50);

	private static final String PATH = "http://localhost:8080/codecrushing/home.xhtml";
	
	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		Purchase p = dao.findByUUID(uuid);
		
		JMSProducer producer = jmsContext.createProducer();
		
		executor.submit(() -> {
			try {
				String message = pg.pay(p.getTotal());	
				
				producer.send(dest, p.getUUID());
				
				URI uri = UriBuilder.fromPath(PATH).queryParam("status", message).build();	
				Response response = Response.seeOther(uri).build();
				ar.resume(response);
			} catch (Exception e) {
				ar.resume(new WebApplicationException(e));
			}
		});
	}
}
