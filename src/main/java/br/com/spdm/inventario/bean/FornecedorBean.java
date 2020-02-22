package br.com.spdm.inventario.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import br.com.spdm.inventario.dao.DAO;
import br.com.spdm.inventario.dao.FornecedorDao;
import br.com.spdm.inventario.model.Fornecedor;

@ManagedBean
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedor = new Fornecedor();

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return new DAO<Fornecedor>(Fornecedor.class).listaTodos();
	}

	public String getFornecedorEscolhido() {
		return fornecedor != null && fornecedor.getId() != null ? fornecedor.toString() : "Classe não escolhida";
	}

	public void salvar() {
		System.out.println("Gravando fornecedor " + this.fornecedor.getNome());

		boolean fornecedorExistente = new FornecedorDao().fornecedorExistente(this.fornecedor);

		if (fornecedorExistente) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Já existe um Fornecedor com o nome " + this.fornecedor.getNome() + "!", null));

		} else if (this.fornecedor.getId() == null) {
			new DAO<Fornecedor>(Fornecedor.class).adiciona(this.fornecedor);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Fornecedor " + "'" + fornecedor.getNome() + "'" + " cadastrado!"));
		} else {
			new DAO<Fornecedor>(Fornecedor.class).atualiza(this.fornecedor);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Fornecedor " + fornecedor.getNome() + " alterado!"));
		}

		this.fornecedor = new Fornecedor();
		// return "equipamento?faces-redirect=true";
	}

	public void remover(Fornecedor fornecedor) {
		System.out.println("Removendo Fornecedor " + fornecedor.getNome());
		
		try {
			new DAO<Fornecedor>(Fornecedor.class).remove(fornecedor);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Fornecedor " + fornecedor.getNome() + " removido!"));
			
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível remover este fornecedor!", null));
		}
	}
}
