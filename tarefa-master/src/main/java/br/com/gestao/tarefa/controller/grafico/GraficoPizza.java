package br.com.gestao.tarefa.controller.grafico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import br.com.gestao.tarefa.repository.TarefaRepository;
import br.com.gestao.tarefa.repository.entity.TarefaEntity;

@Named
@RequestScoped
@SuppressWarnings("serial")
public class GraficoPizza implements Serializable {

	@Inject
	private TarefaRepository tarefaRepository;

	private PieChartModel pieModel1;
	private PieChartModel pieModel2;

	private Calendar cal = Calendar.getInstance();

	private List<TarefaEntity> ListasTarefas;

	@PostConstruct
	@SuppressWarnings("deprecation")
	public void init() {
		int year = 0;
		year = this.cal.get(Calendar.YEAR);
		this.ListasTarefas = this.tarefaRepository.filtrados(new Date("01/01/" + Integer.toString(year)),
				new Date("31/12/" + Integer.toString(year)));
		
		createPieModels();
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void createPieModels() {
		createPieModel1();
		createPieModel2();
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		pieModel1.set("Custo Alto", getCustoAlto());
		pieModel1.set("Custo Baixo", getCustoBaixo());

		pieModel1.setTitle("Valores no Ano");
		pieModel1.setLegendPosition("w");
		pieModel1.setShadow(true);
	}

	private void createPieModel2() {
		pieModel2 = new PieChartModel();

		pieModel2.set("Brand 1", 540);
		pieModel2.set("Brand 2", 325);

		pieModel2.setTitle("Custom Pie");
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
		pieModel2.setShadow(false);
	}

	private BigDecimal getCustoAlto() {
		BigDecimal valor = BigDecimal.ZERO;
		for (TarefaEntity tarefa : this.ListasTarefas) {
			if (tarefa.getCorCusto().equals("MAIOR"))
				valor = valor.add(tarefa.getCusto());
		}

		return valor;
	}

	private BigDecimal getCustoBaixo() {
		BigDecimal valor = BigDecimal.ZERO;
		for (TarefaEntity tarefa : this.ListasTarefas) {
			if (tarefa.getCorCusto().equals("MENOR"))
				valor = valor.add(tarefa.getCusto());
		}
		return valor;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel1;
	}

	public List<TarefaEntity> getListasTarefas() {
		return ListasTarefas;
	}

	public void setListasTarefas(List<TarefaEntity> listasTarefas) {
		ListasTarefas = listasTarefas;
	}

}
