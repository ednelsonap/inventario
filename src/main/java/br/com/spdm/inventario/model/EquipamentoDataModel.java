package br.com.spdm.inventario.model;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.spdm.inventario.dao.EquipamentoDao;

public class EquipamentoDataModel extends LazyDataModel<Equipamento>{

	private static final long serialVersionUID = 1L;

	@Inject
	private EquipamentoDao equipamentoDao;
	
	public EquipamentoDataModel() {
	    super.setRowCount(equipamentoDao.quantidadeDeElementos());
	}
	
	@Override
	public List<Equipamento> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String titulo = (String) filtros.get("titulo");
		return equipamentoDao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
	}
}
