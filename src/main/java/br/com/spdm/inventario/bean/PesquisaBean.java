package br.com.spdm.inventario.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import br.com.spdm.inventario.dao.EquipamentoDao;
import br.com.spdm.inventario.model.Equipamento;

@ManagedBean
@ViewScoped
public class PesquisaBean {

	private Equipamento equipamento = new Equipamento();
	private List<Equipamento> equipamentosEncontrados = new ArrayList<>();

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	
	
}
