package br.com.spdm.inventario.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.spdm.inventario.model.Categoria;
import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.Equipamento;
import br.com.spdm.inventario.model.Fornecedor;
import br.com.spdm.inventario.model.Unidade;

public class EquipamentoDao {

	public boolean patrimonioExistente(Equipamento equipamento) {

		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Equipamento> query = em.createQuery(
				" select e from Equipamento e " + " where e.codigoPatrimonio = :pPatrimonio", Equipamento.class);

		query.setParameter("pPatrimonio", equipamento.getCodigoPatrimonio());

		try {
			Equipamento resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		em.close();

		return true;
	}

	public boolean nomeExistente(Equipamento equipamento) {

		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Equipamento> query = em.createQuery(" select e from Equipamento e " + " where e.nome = :pNome",
				Equipamento.class);

		query.setParameter("pNome", equipamento.getNome());

		try {
			Equipamento resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		em.close();

		return true;
	}

	public List<Equipamento> getEquipamentos(String patrimonio, String nome, Integer categoriaId,
			Integer fornecedorId, Integer unidadeId, Integer departamentoId, String status) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Equipamento> query = criteriaBuilder.createQuery(Equipamento.class);
		Root<Equipamento> root = query.from(Equipamento.class);
		
		Path<String> patrimonioPath = root.<String> get("patrimonio");
		Path<String> nomePath = root.<String> get("nome");
		Path<Integer> categoriaPath = root.<Categoria> get("categoria").<Integer> get("id");
		Path<Integer> fornecedorPath = root.<Fornecedor> get("fornecedor").<Integer> get("id");
		Path<Integer> unidadePath = root.<Unidade> get("unidade").<Integer> get("id");
		Path<Integer> departamentoPath = root.<Departamento> get("departamento").<Integer> get("id");
	/*	Path<String> statusPath = root.<String> get("status");*/

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!nome.isEmpty()) {
			Predicate nomeIgual = criteriaBuilder.equal(nomePath, "%" + nome + "%");
			predicates.add(nomeIgual);
		}
		
		if (!patrimonio.isEmpty()) {
			Predicate patrimonioIgual = criteriaBuilder.equal(patrimonioPath, "%" + patrimonio + "%");
			predicates.add(patrimonioIgual);
		}
		
		if (categoriaId != null) {
			Predicate categoriaIgual = criteriaBuilder.equal(categoriaPath, categoriaId);
			predicates.add(categoriaIgual);
		}

		if (fornecedorId != null) {
			Predicate fornecedorIgual = criteriaBuilder.equal(fornecedorPath, fornecedorId);
			predicates.add(fornecedorIgual);
		}
		
		if (unidadeId != null) {
			Predicate unidadeIgual = criteriaBuilder.equal(unidadePath, unidadeId);
			predicates.add(unidadeIgual);
		}
		
		if (departamentoId != null) {
			Predicate departamentoIgual = criteriaBuilder.equal(departamentoPath, departamentoId);
			predicates.add(departamentoIgual);
		}
		
	/*	if (!status.isEmpty()) {
			Predicate statusIgual = criteriaBuilder.equal(statusPath, "%" + status + "%");
			predicates.add(statusIgual);
		}*/
		
		query.where((Predicate[]) predicates.toArray(new Predicate[0]));

		TypedQuery<Equipamento> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

	}
}
