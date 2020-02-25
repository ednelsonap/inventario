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

import br.com.spdm.inventario.dao.UnidadeDao;
import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.Unidade;

@Named
@ViewScoped
public class UnidadeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Unidade unidade = new Unidade();
	private Departamento departamento = new Departamento();
	
	@Inject
	private UnidadeDao unidadeDao;
	@Inject
	private FacesContext context;
	
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getUnidadeEscolhida() {
		return unidade != null && unidade.getId() != null ? unidade.toString() : "Classe não escolhida";
	}

	@Transactional
	public void salvar() {
		System.out.println("Gravando unidade " + this.unidade.getNome());

		boolean unidadeExistente = new UnidadeDao().unidadeExistente(this.unidade);

		if (unidadeExistente) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Já existe uma unidade com o nome " + this.unidade.getNome() + "!", null));

		} else if (this.unidade.getId() == null) {
			unidadeDao.adiciona(this.unidade);
			context.addMessage(null, new FacesMessage("Unidade cadastrada!"));

		} else {
			unidadeDao.atualiza(this.unidade);
			context.addMessage(null, new FacesMessage("Unidade alterada!"));
		}

		this.unidade = new Unidade();
	}

	public List<Unidade> getUnidades() {
		return unidadeDao.listaTodos();
	}

	public List<Departamento> getDepartamentosDaUnidade() {
		return this.unidade.getDepartamentos();
	}

	@Transactional
	public void remover(Unidade unidade) {
		System.out.println("Removendo Unidade " + unidade.getNome());
		try {
			unidadeDao.remove(unidade);
			context.addMessage(null,
					new FacesMessage("Unidade " + unidade.getNome() + " removida!"));
		} catch (PersistenceException e) {
			context.addMessage(null,
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
