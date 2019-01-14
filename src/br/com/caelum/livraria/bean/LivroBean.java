package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.model.Autor;
import br.com.caelum.livraria.model.Livro;

@ViewScoped
@ManagedBean
public class LivroBean {
	
	private Livro livro = new Livro();
	private Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public String goToCadastroAutor() {
		// Para que a url seja alterada também no navegador utiliza-se "?faces-redirect=true"
		return "autor?faces-redirect=true";
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Livro> listAllLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public void remover(Livro toRemove) {
		new DAO<Livro>(Livro.class).remove(toRemove);
	}
	
	public void editar(Livro toEdit) {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(toEdit.getId());
	}
	
	public void removerAutor(Autor toRemove) {
		this.livro.removerAutor(toRemove);
	}
	
	public void associaAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}
	
	public void gravar() {
		
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		} else {
			
			if (this.livro.getId() == null) {
				new DAO<Livro>(Livro.class).adiciona(this.livro);
			} else {
				new DAO<Livro>(Livro.class).atualiza(this.livro);
			}
			
		}
		
		this.livro = new Livro();
		
	}
	
	public void beginsWithOne(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String valor = value.toString();
		
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deve começar com dígito 1."));
		}
		
	}

}
