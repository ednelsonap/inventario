package br.com.spdm.inventario.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.spdm.inventario.model.Equipamento;

@Named
@ViewScoped
public class PesquisaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Equipamento equipamento = new Equipamento();
	private List<Equipamento> equipamentosEncontrados = new ArrayList<>();

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	
	
}
