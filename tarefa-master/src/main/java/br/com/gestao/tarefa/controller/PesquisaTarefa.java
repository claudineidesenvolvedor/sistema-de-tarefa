package br.com.gestao.tarefa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.com.gestao.tarefa.repository.TarefaRepository;
import br.com.gestao.tarefa.repository.entity.TarefaEntity;
import br.com.gestao.tarefa.service.GestaoCadastroTarefa;
import br.com.gestao.tarefa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaTarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TarefaRepository tarefaRepository;

	@Inject
	private GestaoCadastroTarefa gestaoTarefas;

	private TarefaEntity tarefa;
	private TarefaEntity tarefaSelecionado;

	private List<TarefaEntity> tarefas;

	public PesquisaTarefa() {
		tarefa = new TarefaEntity();
		tarefaSelecionado = new TarefaEntity();
	}

	public void pesquisar() {
		tarefas = tarefaRepository.listaTarefa();
	}

	public void excluir() {

		tarefaRepository.remover(tarefaSelecionado);

		tarefas.remove(tarefaSelecionado);

		FacesUtil.addInfoMessage("tarefa " + tarefa.getId() + " excluído com sucesso.");
	}

	public TarefaEntity getTarefa() {
		return tarefa;
	}

	public void setTarefa(TarefaEntity tarefa) {
		this.tarefa = tarefa;
	}

	public List<TarefaEntity> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<TarefaEntity> tarefas) {
		this.tarefas = tarefas;
	}

	public TarefaEntity getTarefaSelecionado() {
		return tarefaSelecionado;
	}

	public void setTarefaSelecionado(TarefaEntity tarefaSelecionado) {
		this.tarefaSelecionado = tarefaSelecionado;
	}

	public void onRowEdit(RowEditEvent<TarefaEntity> event) {
		//TarefaEntity tarefa = tarefaRepository.buscaPorCod(event.getObject().getId());
		//this.tarefa = this.tarefas.salvar(this.tarefa);
		gestaoTarefas.salvar(event.getObject());
		FacesMessage msg = new FacesMessage("Tarefa editada", String.valueOf(event.getObject().getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent<TarefaEntity> event) {
		FacesMessage msg = new FacesMessage("Edit cancelada", String.valueOf(event.getObject().getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@SuppressWarnings("rawtypes")
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
