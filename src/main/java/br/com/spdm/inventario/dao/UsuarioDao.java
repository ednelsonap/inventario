package br.com.spdm.inventario.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Usuario;

public class UsuarioDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Usuario> dao;
	
	@PostConstruct
	void init(){
		this.dao = new DAO<Usuario>(this.em, Usuario.class);
	}
	
	//Métodos Delegados de DAO
	public void adiciona(Usuario t) {
		dao.adiciona(t);
	}

	public void remove(Usuario t) {
		dao.remove(t);
	}

	public void atualiza(Usuario t) {
		dao.atualiza(t);
	}

	public List<Usuario> listaTodos() {
		return dao.listaTodos();
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	
	public boolean existe(Usuario usuario) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				  " select u from Usuario u "
				+ " where u.login = :pLogin and u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado =  query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		
		em.close();
		
		return true;
	}

}
