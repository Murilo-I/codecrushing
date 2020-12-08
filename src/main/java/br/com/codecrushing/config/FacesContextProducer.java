package br.com.codecrushing.config;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {

	@RequestScoped
	@Produces
	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
}
