package br.com.gestao.tarefa.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gestao.tarefa.repository.entity.TarefaEntity;
import br.com.gestao.tarefa.service.GestaoCadastroTarefa;
import br.com.gestao.tarefa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoCadastroTarefa tarefas;

	private TarefaEntity tarefa;

	public CadastroTarefa() {
		tarefa = new TarefaEntity();
	}

	public void salvar() {
		this.tarefa = this.tarefas.salvar(this.tarefa);

		FacesUtil.addInfoMessage("Tarefa salvo com sucesso!");

	}

	public TarefaEntity getTarefa() {
		return tarefa;
	}

	public void setTarefa(TarefaEntity tarefa) {
		this.tarefa = tarefa;
	}

	public boolean isEditando() {
		return tarefa.getId() != null;
	}
}
