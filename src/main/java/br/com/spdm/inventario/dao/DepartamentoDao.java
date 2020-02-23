package br.com.spdm.inventario.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.spdm.inventario.model.Departamento;

public class DepartamentoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;
	
	private DAO<Departamento> dao;
	
	@PostConstruct
	void init(){
		this.dao = new DAO<Departamento>(this.em, Departamento.class);
	}

	//Métodos Delegados de DAO
	public void adiciona(Departamento t) {
		dao.adiciona(t);
	}
	
	public void remove(Departamento t) {
		dao.remove(t);
	}

	public void atualiza(Departamento t) {
		dao.atualiza(t);
	}

	public List<Departamento> listaTodos() {
		return dao.listaTodos();
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	public int quantidadeDeElementos() {
		return dao.quantidadeDeElementos();
	}
	

	public List<Departamento> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}

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
		
		return true;
	}

}
