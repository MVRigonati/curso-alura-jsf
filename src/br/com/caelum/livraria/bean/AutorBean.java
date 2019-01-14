package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.model.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> listAll() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void remover(Autor toRemove) {
		new DAO<Autor>(Autor.class).remove(toRemove);
	}
	
	public void editar(Autor toEdit) {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(toEdit.getId());
	}

	public void gravar() {
		
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		
		// Limpa campo preenchido
		this.autor = new Autor();
		
	}
	
	public String goToCadastroLivro() {
		return "livro?faces-redirect=true";
	}
	
	public void loadById() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(this.autor.getId());
	}
	
}
