package br.com.spdm.inventario.model;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.spdm.inventario.dao.DepartamentoDao;

public class DepartamentoDataModel extends LazyDataModel<Departamento>{

	private static final long serialVersionUID = 1L;

	@Inject
	private DepartamentoDao departamentoDao;
	
	public DepartamentoDataModel() {
	    super.setRowCount(departamentoDao.quantidadeDeElementos());
	}
	
	@Override
	public List<Departamento> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String titulo = (String) filtros.get("titulo");
		return departamentoDao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
	}
}