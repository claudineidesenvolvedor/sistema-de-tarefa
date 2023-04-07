package br.com.gestao.tarefa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestao.tarefa.repository.TarefaRepository;
import br.com.gestao.tarefa.repository.entity.TarefaEntity;
import br.com.gestao.tarefa.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = TarefaEntity.class)
public class TarefaConverter implements Converter {

	//@Inject
	private TarefaRepository tarefas;
	
	public TarefaConverter() {
		this.tarefas = CDIServiceLocator.getBean(TarefaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TarefaEntity retorno = null;

		if (value != null) {
			Integer cod = Integer.valueOf(value);
			retorno = this.tarefas.buscaPorCod(cod);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			TarefaEntity entity = (TarefaEntity) value;
			return entity.getId() == null ? null : entity.getId().toString();
		}
		return "";
	}

}