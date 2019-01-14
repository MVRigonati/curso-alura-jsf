package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.model.Usuario;

public class UserDAO extends DAO<Usuario> {

	public UserDAO() {
		super(Usuario.class);
	}
	
	public boolean userExists(Usuario user) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		TypedQuery<Usuario> findUserQuery = em.createNamedQuery("User.findUser", Usuario.class);
		
		findUserQuery.setParameter("pEmail", user.getEmail());
		findUserQuery.setParameter("pPassword", user.getPassword());
		
		boolean result = tryToGetResult(findUserQuery);
		
		em.close();
		return result;
		
	}

	private boolean tryToGetResult(TypedQuery<Usuario> findUserQuery) {
		
		try {
			findUserQuery.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		return true;
		
	}

}
