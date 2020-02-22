package br.com.spdm.inventario.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import br.com.spdm.inventario.dao.DAO;
import br.com.spdm.inventario.model.Categoria;

@ManagedBean
@ViewScoped
public class CategoriaBean {

	private Categoria categoria = new Categoria();

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return new DAO<Categoria>(Categoria.class).listaTodos();
	}

	public String getCategoriaEscolhida() {
		return categoria != null && categoria.getId() != null ? categoria.toString() : "Classe não escolhida";
	}

	public void salvar() {
		System.out.println("Gravando categoria " + this.categoria.getNome());

		if (this.categoria.getId() == null) {
			new DAO<Categoria>(Categoria.class).adiciona(this.categoria);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Categoria " + categoria.getNome() + " salva!"));
		} else {
			new DAO<Categoria>(Categoria.class).atualiza(this.categoria);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Categoria " + categoria.getNome() + " alterada!"));
		}

		this.categoria = new Categoria();
		// return "equipamento?faces-redirect=true";
	}

	public void remover(Categoria categoria) {
		System.out.println("Removendo Categoria " + categoria.getNome());
		
		try {
			new DAO<Categoria>(Categoria.class).remove(categoria);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Categoria " + categoria.getNome() + " removida!"));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível remover esta categoria!", null));
		}
	}
}
