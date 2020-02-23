package br.com.spdm.inventario.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.spdm.inventario.model.Equipamento;

@Named
@ViewScoped
public class InicioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Equipamento equipamento = new Equipamento();

	public Equipamento getEquipamento() {
		return equipamento;
	}
}
