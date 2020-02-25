package br.com.spdm.inventario.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import br.com.spdm.inventario.dao.FornecedorDao;
import br.com.spdm.inventario.model.Fornecedor;

@Named
@ViewScoped
public class FornecedorBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor = new Fornecedor();

	@Inject
	private FornecedorDao fornecedorDao;
	@Inject
	private FacesContext context;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedorDao.listaTodos();
	}

	public String getFornecedorEscolhido() {
		return fornecedor != null && fornecedor.getId() != null ? fornecedor.toString() : "Classe não escolhida";
	}

	@Transactional
	public void salvar() {
		System.out.println("Gravando fornecedor " + this.fornecedor.getNome());

		boolean fornecedorExistente = new FornecedorDao().fornecedorExistente(this.fornecedor);

		if (fornecedorExistente) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Já existe um Fornecedor com o nome " + this.fornecedor.getNome() + "!", null));

		} else if (this.fornecedor.getId() == null) {
			fornecedorDao.adiciona(this.fornecedor);
			context.addMessage(null,
					new FacesMessage("Fornecedor " + "'" + fornecedor.getNome() + "'" + " cadastrado!"));
		} else {
			fornecedorDao.atualiza(this.fornecedor);
			context.addMessage(null,
					new FacesMessage("Fornecedor " + fornecedor.getNome() + " alterado!"));
		}

		this.fornecedor = new Fornecedor();
		// return "equipamento?faces-redirect=true";
	}

	@Transactional
	public void remover(Fornecedor fornecedor) {
		System.out.println("Removendo Fornecedor " + fornecedor.getNome());
		
		try {
			fornecedorDao.remove(fornecedor);
			context.addMessage(null,
					new FacesMessage("Fornecedor " + fornecedor.getNome() + " removido!"));
			
		} catch (PersistenceException e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível remover este fornecedor!", null));
		}
	}
}
