package br.com.caelum.livraria.bean;

import java.util.Map;

import javax.faces.application.FacesMessage;
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
		
		FacesContext context = FacesContext.getCurrentInstance();
		this.user = new UserDAO().getUserBy(this.user.getEmail(), this.user.getPassword());
		
		if (this.user != null) {
			saveUserOnSession(context);
			return "livro?faces-redirect=true";
		}
		
		// Para manter esta mensagem para duas requisições
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário ou senha incorretos."));
		
		// A mensagem é perdida se não foi guardada por mais de uma requisição
		return "login?faces-redirect=true";
		
	}
	
	private void saveUserOnSession(FacesContext context) {
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		sessionMap.put("user", this.user);
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("user");
		return "login?faces-redirect=true";
	}
	
}
