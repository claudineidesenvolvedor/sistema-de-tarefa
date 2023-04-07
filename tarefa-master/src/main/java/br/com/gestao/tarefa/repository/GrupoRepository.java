package br.com.gestao.tarefa.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.gestao.tarefa.repository.entity.GrupoEntity;

public class GrupoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	/*
	 * CONSULTA UM UC CADASTRADA PELO ID
	 * 
	 * @param cod
	 * 
	 * @return GrupoEntity
	 */
	public GrupoEntity buscaPorCod(Integer cod) {
		return entityManager.find(GrupoEntity.class, cod);

	}

}
