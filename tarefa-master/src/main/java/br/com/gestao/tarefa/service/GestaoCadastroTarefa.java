package br.com.gestao.tarefa.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.gestao.tarefa.repository.TarefaRepository;
import br.com.gestao.tarefa.repository.entity.TarefaEntity;
import br.com.gestao.tarefa.util.jpa.Transactional;

public class GestaoCadastroTarefa implements Serializable {

	private static final long serialVersionUID = 2853169268942998607L;

	@Inject
	private TarefaRepository tarefa;
	
	@Transactional
	public TarefaEntity salvar(TarefaEntity entity) {
		return tarefa.guardar(entity);
	}
}
