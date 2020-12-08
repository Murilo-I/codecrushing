package br.com.codecrushing.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.codecrushing.daos.SecurityDao;
import br.com.codecrushing.models.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;
	@Inject
	private SecurityDao dao;
	private SystemUser systemUser;
	
	@PostConstruct
	public void loadPrincipal() {
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			String email = request.getUserPrincipal().getName();
			systemUser = dao.findByEmail(email);
		}
	}
	
	public SystemUser get() {
		return systemUser;
	}
	
	public boolean hasRole(String role) {
		return request.isUserInRole(role);
	}
	
	public String logout() {
		request.getSession().invalidate();
		return "/books/bookForm.xhtm?faces-redirect=true";
	}
}
