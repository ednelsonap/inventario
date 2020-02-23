package br.com.spdm.inventario.bean;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.spdm.inventario.dao.UsuarioDao;
import br.com.spdm.inventario.model.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}
	
	@Inject
	FacesContext context;
	
	public String entrar() {
		System.out.println("fazendo login do usuario " + this.usuario.getLogin());
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = new UsuarioDao().existe(this.usuario);
		if(existe ) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "equipamento?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
}
