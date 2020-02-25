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

import br.com.spdm.inventario.dao.DepartamentoDao;
import br.com.spdm.inventario.dao.UnidadeDao;
import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.Unidade;

@Named
@ViewScoped
public class DepartamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Departamento departamento = new Departamento();
	
	private Integer unidadeId;

	private Unidade unidade = new Unidade();

	@Inject
	private DepartamentoDao departamentoDao;
	@Inject
	private UnidadeDao unidadeDao;
	
	@Inject
	private FacesContext context;
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Transactional
	public void salvar() {
		System.out.println("Gravando departamento " + this.departamento.getNome());

		boolean departamentoExistente = new DepartamentoDao().departamentoExistente(this.departamento);

		// para alteração com duplicidade de departamento
		if (departamentoExistente && this.departamento.getId() != null) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Departamento já existente! ", null));

			// para inserção com duplicidade de departamento
		}
		if (departamentoExistente && this.departamento.getId() == null) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Departamento já existente! ", null));

			// para inserção em que não haja duplicidade
		} else if (this.departamento.getId() == null) {
			departamentoDao.adiciona(this.departamento);
			context.addMessage(null, new FacesMessage("Departamento cadastrado! "));

			// para alteração em que não haja duplicidade
		} else {
			departamentoDao.atualiza(this.departamento);
		}

		this.departamento = new Departamento();
	}

	public List<Departamento> getDepartamentos() {
		return departamentoDao.listaTodos();
	}

	public List<Unidade> getUnidades() {
		return unidadeDao.listaTodos();
	}

	@Transactional
	public void remover(Departamento departamento) {
		System.out.println("Removendo departamento " + departamento.getNome());
		
		try {
			departamentoDao.remove(departamento);
			context.addMessage(null,
					new FacesMessage("Departamento " + departamento.getNome() + " removido!"));
			
		} catch (PersistenceException e) {
			context.addMessage(null,
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
}
