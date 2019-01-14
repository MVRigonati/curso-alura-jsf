package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
		
		if (new UserDAO().userExists(this.user)) {
			return "livro?faces-redirect=true";
		}

		return null;
		
	}
	
}
