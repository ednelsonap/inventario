package br.com.spdm.inventario.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import br.com.spdm.inventario.dao.DAO;
import br.com.spdm.inventario.dao.UnidadeDao;
import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.Unidade;

@ManagedBean
@ViewScoped
public class UnidadeBean {

	private Unidade unidade = new Unidade();
	private Departamento departamento = new Departamento();

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getUnidadeEscolhida() {
		return unidade != null && unidade.getId() != null ? unidade.toString() : "Classe não escolhida";
	}

	public void salvar() {
		System.out.println("Gravando unidade " + this.unidade.getNome());

		boolean unidadeExistente = new UnidadeDao().unidadeExistente(this.unidade);

		if (unidadeExistente) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Já existe uma unidade com o nome " + this.unidade.getNome() + "!", null));

		} else if (this.unidade.getId() == null) {
			new DAO<Unidade>(Unidade.class).adiciona(this.unidade);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unidade cadastrada!"));

		} else {
			new DAO<Unidade>(Unidade.class).atualiza(this.unidade);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unidade alterada!"));
		}

		this.unidade = new Unidade();
	}

	public List<Unidade> getUnidades() {
		return new DAO<Unidade>(Unidade.class).listaTodos();
	}

	public List<Departamento> getDepartamentosDaUnidade() {
		return this.unidade.getDepartamentos();
	}

	public void remover(Unidade unidade) {
		System.out.println("Removendo Unidade " + unidade.getNome());
		try {
			new DAO<Unidade>(Unidade.class).remove(unidade);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Unidade " + unidade.getNome() + " removida!"));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível remover esta unidade!", null));
		}
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
