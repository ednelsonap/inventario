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

import br.com.spdm.inventario.dao.CategoriaDao;
import br.com.spdm.inventario.model.Categoria;

@Named
@ViewScoped
public class CategoriaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Categoria categoria = new Categoria();

	@Inject
	private CategoriaDao categoriaDao;
	
	@Inject
	FacesContext context;
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categoriaDao.listaTodos();
	}

	public String getCategoriaEscolhida() {
		return categoria != null && categoria.getId() != null ? categoria.toString() : "Classe não escolhida";
	}

	@Transactional
	public void salvar() {
		System.out.println("Gravando categoria " + this.categoria.getNome());

		if (this.categoria.getId() == null) {
			categoriaDao.adiciona(this.categoria);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Categoria " + categoria.getNome() + " salva!"));
		} else {
			categoriaDao.atualiza(this.categoria);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Categoria " + categoria.getNome() + " alterada!"));
		}

		this.categoria = new Categoria();
		// return "equipamento?faces-redirect=true";
	}

	@Transactional
	public void remover(Categoria categoria) {
		System.out.println("Removendo Categoria " + categoria.getNome());
		
		try {
			categoriaDao.remove(categoria);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Categoria " + categoria.getNome() + " removida!"));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível remover esta categoria!", null));
		}
	}
}
