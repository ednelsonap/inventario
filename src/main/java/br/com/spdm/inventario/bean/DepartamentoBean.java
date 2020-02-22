package br.com.spdm.inventario.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import br.com.spdm.inventario.dao.DAO;
import br.com.spdm.inventario.dao.DepartamentoDao;
import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.DepartamentoDataModel;
import br.com.spdm.inventario.model.Unidade;

@ManagedBean
@ViewScoped
public class DepartamentoBean {

	private Departamento departamento = new Departamento();
	private Integer unidadeId;

	private Unidade unidade = new Unidade();

	private DepartamentoDataModel departamentoDataModel = new DepartamentoDataModel();

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void salvar() {
		System.out.println("Gravando departamento " + this.departamento.getNome());

		boolean departamentoExistente = new DepartamentoDao().departamentoExistente(this.departamento);

		// para alteração com duplicidade de departamento
		if (departamentoExistente && this.departamento.getId() != null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Departamento já existente! ", null));

			// para inserção com duplicidade de departamento
		}
		if (departamentoExistente && this.departamento.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Departamento já existente! ", null));

			// para inserção em que não haja duplicidade
		} else if (this.departamento.getId() == null) {
			new DAO<Departamento>(Departamento.class).adiciona(this.departamento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Departamento cadastrado! "));

			// para alteração em que não haja duplicidade
		} else {
			new DAO<Departamento>(Departamento.class).atualiza(this.departamento);
		}

		this.departamento = new Departamento();
	}

	public List<Departamento> getDepartamentos() {
		return new DAO<Departamento>(Departamento.class).listaTodos();
	}

	public List<Unidade> getUnidades() {
		return new DAO<Unidade>(Unidade.class).listaTodos();
	}

	public void remover(Departamento departamento) {
		System.out.println("Removendo departamento " + departamento.getNome());
		
		try {
			new DAO<Departamento>(Departamento.class).remove(departamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Departamento " + departamento.getNome() + " removido!"));
			
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível remover este departamento!", null));
		}
	}

	public Integer getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId(Integer unidadeId) {
		this.unidadeId = unidadeId;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public DepartamentoDataModel getDepartamentoDataModel() {
		return departamentoDataModel;
	}

	public void setDepartamentoDataModel(DepartamentoDataModel departamentoDataModel) {
		this.departamentoDataModel = departamentoDataModel;
	}
}
