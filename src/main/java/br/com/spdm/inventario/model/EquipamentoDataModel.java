package br.com.spdm.inventario.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.spdm.inventario.dao.DAO;

public class EquipamentoDataModel extends LazyDataModel<Equipamento>{

	private static final long serialVersionUID = 1L;

	DAO<Equipamento> dao = new DAO<Equipamento>(Equipamento.class);
	public EquipamentoDataModel() {
	    super.setRowCount(dao.quantidadeDeElementos());
	}
	
	@Override
	public List<Equipamento> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String titulo = (String) filtros.get("titulo");
		return dao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
	}
}
