package br.com.caelum.livraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "User.findUser", query = "SELECT u FROM Usuario u WHERE u.email = :pEmail and u.password = :pPassword")
public class Usuario {

	@Id
	@GeneratedValue
	private Integer id;
	private String email;
	private String password;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
