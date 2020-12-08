package br.com.codecrushing.beans;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.codecrushing.models.Promotion;
import br.com.codecrushing.websockets.PromotionEndPoints;

@Model
public class AdminPromotionBean {

	private Promotion promo = new Promotion();
	@Inject
	private PromotionEndPoints pep;
	@Inject
	private FacesContext context;
	
	public String save() {
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Promoção cadastrada com sucesso"));

		pep.send(promo);
		
		return "home?faces-redirect=true";
	}
	
	public Promotion getPromo() {
		return promo;
	}
	public void setPromo(Promotion promo) {
		this.promo = promo;
	}
}
