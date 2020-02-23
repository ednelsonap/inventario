package br.com.spdm.inventario.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Categoria;

public class CategoriaDao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Categoria> dao;
	
	@PostConstruct
	void init(){
		this.dao = new DAO<Categoria>(this.em, Categoria.class);
	}
	
	//Métodos Delegados de DAO
	public void adiciona(Categoria categoria) {
		dao.adiciona(categoria);
	}

	public void remove(Categoria categoria) {
		dao.remove(categoria);
	}

	public void atualiza(Categoria categoria) {
		dao.atualiza(categoria);
	}

	public List<Categoria> listaTodos() {
		return dao.listaTodos();
	}

	public int contaTodos() {
		return dao.contaTodos();
	}


	public boolean existe(Categoria categoria) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Categoria> query = em.createQuery(
				  " select c from Categoria c "
				+ " where c.nome = :pNome", Categoria.class);
		
		query.setParameter("pNome", categoria.getNome());
		
		try {
			Categoria resultado =  query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		
		return true;
	}
}
