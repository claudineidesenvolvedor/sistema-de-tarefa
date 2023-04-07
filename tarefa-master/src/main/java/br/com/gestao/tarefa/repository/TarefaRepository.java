package br.com.gestao.tarefa.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.gestao.tarefa.repository.entity.TarefaEntity;
import br.com.gestao.tarefa.service.NegocioException;
import br.com.gestao.tarefa.util.jpa.Transactional;

public class TarefaRepository implements Serializable {

	private static final long serialVersionUID = 3493041292852274095L;

	@Inject
	private EntityManager manager;

	public TarefaEntity guardar(TarefaEntity entity) {
		return manager.merge(entity);
	}

	@Transactional
	public void remover(TarefaEntity tarefa) {
		try {
			tarefa = buscaPorCod(tarefa.getId());
			manager.remove(tarefa);
			manager.flush();
		} catch (IllegalArgumentException e) {
			throw new NegocioException("tarefa não pode ser excluído.");

		} catch (PersistenceException e) {
			throw new NegocioException("tarefa não pode ser excluído.");
		}

	}

	@SuppressWarnings("unchecked")
	public List<TarefaEntity> listaTarefa() {
		List<TarefaEntity> lista = new ArrayList<TarefaEntity>();
		lista = manager.createNamedQuery("TarefaEntity.findAll").getResultList();
		return lista;
	}

	public TarefaEntity buscaPorCod(Integer cod) {
		return manager.find(TarefaEntity.class, cod);

	}

	@SuppressWarnings({ "unchecked" })
	public List<TarefaEntity> filtrados(Date inicial, Date fim) {
		List<TarefaEntity> lista = new ArrayList<TarefaEntity>();
		try {

			lista = this.manager.createNamedQuery("TarefaEntity.findAno").setParameter("dataCriacaoDe", inicial)
					.setParameter("dataCriacaoAte", fim).getResultList();
		} catch (NoResultException e) {
			// nenhum parcela encontrado
		}
		return lista;

	}
}
