package br.com.spdm.inventario.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Fornecedor;

public class FornecedorDao {

	public boolean fornecedorExistente(Fornecedor fornecedor) {

		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Fornecedor> query = em.createQuery(" select f from Fornecedor f " + " where f.nome = :pNome",
				Fornecedor.class);

		query.setParameter("pNome", fornecedor.getNome());

		try {
			Fornecedor resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		em.close();

		return true;
	}

}
