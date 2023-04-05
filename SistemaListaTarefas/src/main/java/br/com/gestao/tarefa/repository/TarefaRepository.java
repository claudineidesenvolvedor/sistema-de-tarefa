package br.com.gestao.tarefa.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.gestao.tarefa.repository.entity.TarefaEntity;
import br.com.gestao.tarefa.service.NegocioException;
import br.com.gestao.tarefa.util.jpa.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class TarefaRepository implements Serializable {

	private static final long serialVersionUID = 3493041292852274095L;

	@Inject
	private EntityManager manager;

	public TarefaEntity guardar(TarefaEntity cliente) {
		return manager.merge(cliente);
	}

	@Transactional
	public void remover(TarefaEntity tarefa) {
		try {
			tarefa = porId(tarefa.getId());
			manager.remove(tarefa);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tarefa não pode ser excluído.");
		}
	}

	public TarefaEntity porId(Integer id) {
		return this.manager.find(TarefaEntity.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TarefaEntity> listaTarefa() {
		List<TarefaEntity> lista = new ArrayList<TarefaEntity>();
		lista = manager.createNamedQuery("TarefaEntity.findAll").getResultList();
		return lista;
	}
}
