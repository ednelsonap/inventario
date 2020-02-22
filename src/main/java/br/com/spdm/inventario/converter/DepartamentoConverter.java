package br.com.spdm.inventario.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.spdm.inventario.model.Departamento;

@FacesConverter(forClass = Departamento.class)
public class DepartamentoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext faceContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
            return (Departamento) uiComponent.getAttributes().get(value);
        }
		return null;
	}

	@Override
	public String getAsString(FacesContext faceContext, UIComponent uiComponent, Object value) {
		if (value instanceof Departamento) {
            Departamento entity= (Departamento) value;
            if (entity != null && entity instanceof Departamento && entity.getId() != null) {
                uiComponent.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
		return "";
	}
	
}
