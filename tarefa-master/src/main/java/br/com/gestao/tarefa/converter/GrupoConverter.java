package br.com.gestao.tarefa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestao.tarefa.repository.GrupoRepository;
import br.com.gestao.tarefa.repository.entity.GrupoEntity;
import br.com.gestao.tarefa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = GrupoEntity.class)
public class GrupoConverter implements Converter {

	private GrupoRepository grupos;

	public GrupoConverter() {
		grupos = CDIServiceLocator.getBean(GrupoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		GrupoEntity retorno = null;

		if (value != null) {
			Integer cod = Integer.valueOf(value);
			retorno = grupos.buscaPorCod(cod);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			GrupoEntity entity = (GrupoEntity) value;
			return entity.getId() == null ? null : entity.getId().toString();
		}

		return "";
	}

}