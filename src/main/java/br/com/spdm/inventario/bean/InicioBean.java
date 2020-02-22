package br.com.spdm.inventario.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.spdm.inventario.model.Equipamento;

@ManagedBean
@ViewScoped
public class InicioBean {
	private Equipamento equipamento = new Equipamento();

	public Equipamento getEquipamento() {
		return equipamento;
	}
}
