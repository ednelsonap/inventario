package br.com.spdm.inventario.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.spdm.inventario.dao.UsuarioDao;
import br.com.spdm.inventario.model.Usuario;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private FacesContext context;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios(){
		return usuarioDao.listaTodos();
	}
	
	@Transactional
	public void salvar() {
		System.out.println("Gravando usuario " + this.usuario.getNome());

		if(this.usuario.getId() == null) {
			usuarioDao.adiciona(this.usuario);
			context.addMessage(null,
	                new FacesMessage("Usuario " + usuario.getNome() + " salvo!"));
		} else {
			usuarioDao.atualiza(this.usuario);
			context.addMessage(null,
	                new FacesMessage("Usuario " + usuario.getNome() + " alterado!"));
		}

		this.usuario = new Usuario();
		//return "equipamento?faces-redirect=true";
	}
	
	@Transactional
	public void remover(Usuario usuario) {
		System.out.println("Removendo Usuario " + usuario.getNome());
		
		usuarioDao.remove(usuario);
		context.addMessage(null,
				new FacesMessage("Usuario " + usuario.getNome() + " removido!"));
	}
}
