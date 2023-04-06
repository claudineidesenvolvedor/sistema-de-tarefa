package br.com.gestao.tarefa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestao.tarefa.repository.UsuariosRepository;
import br.com.gestao.tarefa.repository.entity.UsuarioEntity;
import br.com.gestao.tarefa.util.cdi.CDIServiceLocator;

@SuppressWarnings("rawtypes")
@FacesConverter(forClass = UsuarioEntity.class)
public class UsuarioConverter implements Converter {

	// @Inject
	private UsuariosRepository usuarios;

	public UsuarioConverter() {
		this.usuarios = (UsuariosRepository) CDIServiceLocator.getBean(UsuariosRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UsuarioEntity retorno = null;

		if (value != null) {
			Integer cod = Integer.valueOf(value);
			retorno = this.usuarios.porId(cod);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			UsuarioEntity entity = (UsuarioEntity) value;
			return entity.getId() == null ? null : entity.getId().toString();

		}
		return "";
	}

}