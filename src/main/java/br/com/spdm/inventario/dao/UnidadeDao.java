package br.com.spdm.inventario.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Unidade;

public class UnidadeDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Unidade> dao;
	
	@PostConstruct
	void init(){
		this.dao = new DAO<Unidade>(this.em, Unidade.class);
	}
	
	//Métodos Delegados do DAO
	public void adiciona(Unidade t) {
		dao.adiciona(t);
	}

	public void remove(Unidade t) {
		dao.remove(t);
	}
	
	public void atualiza(Unidade t) {
		dao.atualiza(t);
	}

	public List<Unidade> listaTodos() {
		return dao.listaTodos();
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	
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
