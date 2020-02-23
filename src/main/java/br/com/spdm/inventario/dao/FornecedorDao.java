package br.com.spdm.inventario.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Fornecedor;

public class FornecedorDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Fornecedor> dao;
	
	@PostConstruct
	void init(){
		this.dao = new DAO<Fornecedor>(this.em, Fornecedor.class);
	}
	
	//Métodos Delegados do DAO
	public void adiciona(Fornecedor t) {
		dao.adiciona(t);
	}

	public void remove(Fornecedor t) {
		dao.remove(t);
	}

	public void atualiza(Fornecedor t) {
		dao.atualiza(t);
	}

	public List<Fornecedor> listaTodos() {
		return dao.listaTodos();
	}

	public int contaTodos() {
		return dao.contaTodos();
	}
	

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

		return true;
	}

}
