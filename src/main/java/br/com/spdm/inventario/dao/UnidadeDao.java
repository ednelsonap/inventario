package br.com.spdm.inventario.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Unidade;

public class UnidadeDao {

	public boolean unidadeExistente(Unidade unidade) {

		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Unidade> query = em.createQuery(" select u from Unidade u " + " where u.nome = :pNome",
				Unidade.class);

		query.setParameter("pNome", unidade.getNome());

		try {
			Unidade resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		em.close();

		return true;
	}

}
