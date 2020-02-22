package br.com.spdm.inventario.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.Usuario;



public class DepartamentoDao {

	public boolean departamentoExistente(Departamento departamento) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Departamento> query = em.createQuery(
				  " select d from Departamento d where d.nome = :pDepartamento and d.unidade.nome = :pUnidade", Departamento.class);
		
		query.setParameter("pDepartamento", departamento.getNome());
		query.setParameter("pUnidade", departamento.getUnidade().getNome());
		try {
			Departamento resultado =  query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		
		em.close();
		
		return true;
	}

}
