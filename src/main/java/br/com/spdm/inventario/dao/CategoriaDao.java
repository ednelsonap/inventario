package br.com.spdm.inventario.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Categoria;

public class CategoriaDao {
	
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
		
		em.close();
		
		return true;
	}
}
