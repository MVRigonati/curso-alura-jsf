package br.com.caelum.livraria.bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UserDAO;
import br.com.caelum.livraria.model.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario user = new Usuario();

	public Usuario getUser() {
		return user;
	}

	public String login() {
		
		System.out.println("Fazendo login - " + this.user.getEmail());
		
		this.user = new UserDAO().getUserBy(this.user.getEmail(), this.user.getPassword());
		
		if (this.user != null) {
			saveUserOnSession();
			return "livro?faces-redirect=true";
		}

		return null;
		
	}

	private void saveUserOnSession() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		
		sessionMap.put("user", this.user);
		
	}
	
}
