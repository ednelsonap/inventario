package br.com.spdm.inventario.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.primefaces.PrimeFaces;

import br.com.spdm.inventario.dao.CategoriaDao;
import br.com.spdm.inventario.dao.DepartamentoDao;
import br.com.spdm.inventario.dao.EquipamentoDao;
import br.com.spdm.inventario.dao.FornecedorDao;
import br.com.spdm.inventario.dao.UnidadeDao;
import br.com.spdm.inventario.model.Categoria;
import br.com.spdm.inventario.model.Departamento;
import br.com.spdm.inventario.model.Equipamento;
import br.com.spdm.inventario.model.Fornecedor;
import br.com.spdm.inventario.model.Unidade;

@Named
@ViewScoped
public class EquipamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Equipamento equipamento = new Equipamento();
	private Unidade unidade = new Unidade();
	//private Integer categoriaId;
	//private Integer equipamentoId;
	ArrayList<Integer> a = new ArrayList<Integer>();
	
	@Inject
	private EquipamentoDao equipamentoDao;
	@Inject
	private CategoriaDao categoriaDao;
	@Inject
	private FornecedorDao fornecedorDao;
	@Inject
	private DepartamentoDao departamentoDao;
	@Inject
	private UnidadeDao unidadeDao;
	
	@Inject
	FacesContext context;
	
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getEquipamentos(){
		return equipamentoDao.listaTodos();
	}
	
	public List<Categoria> getCategorias() {
		return categoriaDao.listaTodos();
	}
	
	public List<Unidade> getUnidades() {
		return unidadeDao.listaTodos();
	}

	public List<Departamento> getDepartamentos() {
		return departamentoDao.listaTodos();
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedorDao.listaTodos();
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

	@Transactional
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
			equipamentoDao.adiciona(this.equipamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Equipamento " + equipamento.getNome() + " cadastrado com sucesso!"));
		}
		this.equipamento = new Equipamento();
	}

	@Transactional
	public void alterar() {
		System.out.println("Alterando equipamento " + this.equipamento.getNome());

		try {
			equipamentoDao.atualiza(this.equipamento);
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
	
	@Transactional
	public void remover(Equipamento equipamento) {
		System.out.println("Removendo Equipamento " + equipamento.getNome());
		equipamentoDao.remove(equipamento);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Equipamento " + equipamento.getNome() + " removido!"));
	}

/*	public void carregarEquipamentoPelaId() {
		this.equipamento = equipamentoDao.buscaPorId(this.equipamento.getId());
	}*/
	
}
