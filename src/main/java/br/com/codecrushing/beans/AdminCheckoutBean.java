package br.com.codecrushing.beans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.codecrushing.models.Cart;
import br.com.codecrushing.models.Purchase;
import br.com.codecrushing.models.User;

@Model
public class AdminCheckoutBean {

	private User user = new User();
	
	@Inject
	private Cart cart;
	
	@Inject
	private FacesContext context;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) { 
		this.user = user;
	}
	
	@Transactional
	public void finalize() {
		Purchase p = new Purchase();
		p.setUser(this.user);
		cart.finalize(p);
		
		String path = context.getExternalContext().getRequestContextPath();
		HttpServletResponse response = 
				(HttpServletResponse) context.getExternalContext().getResponse();
		
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT); //ou código 307
		response.setHeader("Location", path+"/service/payment?uuid="+p.getUUID());
	}
}
