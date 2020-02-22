package br.com.spdm.inventario.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.spdm.inventario.dao.DAO;
import br.com.spdm.inventario.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios(){
		return new DAO<Usuario>(Usuario.class).listaTodos();
	}
	
	public void salvar() {
		System.out.println("Gravando usuario " + this.usuario.getNome());

		if(this.usuario.getId() == null) {
			new DAO<Usuario>(Usuario.class).adiciona(this.usuario);
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Usuario " + usuario.getNome() + " salvo!"));
		} else {
			new DAO<Usuario>(Usuario.class).atualiza(this.usuario);
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Usuario " + usuario.getNome() + " alterado!"));
		}

		this.usuario = new Usuario();
		//return "equipamento?faces-redirect=true";
	}
	
	public void remover(Usuario usuario) {
		System.out.println("Removendo Usuario " + usuario.getNome());
		
		new DAO<Usuario>(Usuario.class).remove(usuario);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Usuario " + usuario.getNome() + " removido!"));
	}
}
