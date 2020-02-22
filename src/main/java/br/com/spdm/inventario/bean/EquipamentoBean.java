package br.com.spdm.inventario.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.primefaces.PrimeFaces;

import br.com.spdm.inventario.dao.DAO;
import br.com.spdm.inventario.dao.EquipamentoDao;
import br.com.spdm.inventario.model.Categoria;
import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.Equipamento;
import br.com.spdm.inventario.model.EquipamentoDataModel;
import br.com.spdm.inventario.model.Fornecedor;
import br.com.spdm.inventario.model.Unidade;
import br.com.spdm.inventario.model.Usuario;

@Named
@ViewScoped
public class EquipamentoBean {

	private Equipamento equipamento = new Equipamento();
	private Unidade unidade = new Unidade();
	private Usuario usuario = new Usuario();
	private EquipamentoDataModel equipamentoDataModel = new EquipamentoDataModel();
	private Integer categoriaId;
	private Integer equipamentoId;
	ArrayList<Integer> a = new ArrayList<Integer>();
	

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getEquipamentoId() {
		return equipamentoId;
	}

	public void setEquipamentoId(Integer equipamentoId) {
		this.equipamentoId = equipamentoId;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public List<Categoria> getCategorias() {
		return new DAO<Categoria>(Categoria.class).listaTodos();
	}

	public List<Unidade> getUnidades() {
		return new DAO<Unidade>(Unidade.class).listaTodos();
	}

	public List<Departamento> getDepartamentos() {
		return new DAO<Departamento>(Departamento.class).listaTodos();
	}

	public List<Fornecedor> getFornecedores() {
		return new DAO<Fornecedor>(Fornecedor.class).listaTodos();
	}

	public List<Departamento> getDepartamentosDaUnidade() {
		return this.unidade.getDepartamentos();
	}

	/*
	 * public void abrirDialogoNovo() { Map<String, Object> opcoes = new
	 * HashMap<String, Object>(); opcoes.put("modal", true);
	 * opcoes.put("resizable", false); opcoes.put("contentHeight", 400);
	 * opcoes.put("contentWidth", 900);
	 * RequestContext.getCurrentInstance().openDialog("novo_equipamento",
	 * opcoes, null); }
	 */

	public void salvar() {
		System.out.println("Gravando equipamento " + this.equipamento.getNome());

		boolean patrimonioExistente = new EquipamentoDao().patrimonioExistente(this.equipamento);
		boolean nomeExistente = new EquipamentoDao().nomeExistente(this.equipamento);

		// para inserção de patrimonio com duplicidade
		if (patrimonioExistente && this.equipamento.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Já existe um equipamento com este mesmo patrimônio!", null));
			// para inserção de nome com duplicidade
		} else if (nomeExistente && this.equipamento.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Já existe um equipamento com este mesmo nome!", null));
		} else {
			new DAO<Equipamento>(Equipamento.class).adiciona(this.equipamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Equipamento " + equipamento.getNome() + " cadastrado com sucesso!"));
		}

		/*
		 * try { new
		 * DAO<Equipamento>(Equipamento.class).adiciona(this.equipamento);
		 * FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		 * "Equipamento " + equipamento.getNome() + " alterado!"));
		 * 
		 * } catch (PersistenceException e) {
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Não foi possível salvar este equipamento! Verifique se não há duplicidade de nome ou patrimônio."
		 * , null)); }
		 */
		this.equipamento = new Equipamento();
	}

	public void alterar() {
		System.out.println("Alterando equipamento " + this.equipamento.getNome());

		try {
			new DAO<Equipamento>(Equipamento.class).atualiza(this.equipamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Equipamento " + equipamento.getNome() + " cadastrado com sucesso!"));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Não foi possível salvar este equipamento! Verifique se não há duplicidade de nome ou patrimônio.",
							null));
		}
		this.equipamento = new Equipamento();
	}

	public void buscar(String patrimonio, String nome, Integer categoriaId, Integer fornecedorId, Integer unidadeId,
			Integer departamentoId, String status) {
			
	}

	public boolean exibirBotaoAlterar(Equipamento equipamento) {
		if (this.equipamento.getId() != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean exibirBotaoSalvar(Equipamento equipamento) {
		if (this.equipamento.getId() == null) {
			return true;
		} else {
			return false;
		}
	}

	public void limpar() {
		this.equipamento = new Equipamento();
		PrimeFaces.current().resetInputs("formEquipamento:panelGridCadastro");
	}
	
	public void limparPesquisa() {
		this.equipamento = new Equipamento();
		PrimeFaces.current().resetInputs("formPesquisa:panelGridPesquisa");
	}
	
	public void remover(Equipamento equipamento) {
		System.out.println("Removendo Equipamento " + equipamento.getNome());
		new DAO<Equipamento>(Equipamento.class).remove(equipamento);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Equipamento " + equipamento.getNome() + " removido!"));
	}

	public void carregarEquipamentoPelaId() {
		this.equipamento = new DAO<Equipamento>(Equipamento.class).buscaPorId(this.equipamento.getId());
	}

	/*
	 * public int quantidadeDeEquipamentos(){ return new
	 * DAO<Equipamento>(Equipamento.class).contaTodos(); }
	 */
	/*
	 * public Categoria getCategoria() { return categoria; }
	 * 
	 * public void setCategoria(Categoria categoria) { this.categoria =
	 * categoria; }
	 */

	public EquipamentoDataModel getEquipamentoDataModel() {
		return equipamentoDataModel;
	}

	public void setEquipamentoDataModel(EquipamentoDataModel equipamentoDataModel) {
		this.equipamentoDataModel = equipamentoDataModel;
	}

	/*
	 * public Unidade getUnidadeSelecionada() { return unidadeSelecionada; }
	 * 
	 * public void setUnidadeSelecionada(Unidade unidadeSelecionada) {
	 * this.unidadeSelecionada = unidadeSelecionada; }
	 */

}
