package br.com.gestao.tarefa.controller.grafico;

import java.io.Serializable;
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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import br.com.gestao.tarefa.repository.TarefaRepository;
import br.com.gestao.tarefa.repository.entity.TarefaEntity;

@Named
@RequestScoped
@SuppressWarnings("serial")
public class GraficoBarras implements Serializable {

	@Inject
	private TarefaRepository tarefaRepository;

	private BarChartModel barModel;
	private HorizontalBarChartModel horizontalBarModel;
	private Calendar cal = Calendar.getInstance();

	private List<TarefaEntity> ListasTarefas;

	int count;

	@PostConstruct
	@SuppressWarnings("deprecation")
	public void init() {
		int year = 0;
		year = this.cal.get(Calendar.YEAR);
		this.ListasTarefas = this.tarefaRepository.filtrados(new Date("01/01/" + Integer.toString(year)),
				new Date("31/12/" + Integer.toString(year)));

		createBarModels();
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		model.addSeries(getCustoBaixo());
		model.addSeries(getCustoAlto());
		model.setAnimate(true);

		return model;
	}

	@SuppressWarnings("deprecation")
	private ChartSeries getCustoAlto() {
		ChartSeries charSeries = new ChartSeries();
		int jan = 0, fev = 0, mar = 0, abr = 0, mai = 0, jun = 0, julh = 0, agos = 0, sete = 0, out = 0, nov = 0,
				dez = 0;
		charSeries.setLabel("Custo Alto");
		for (TarefaEntity tarefa : this.ListasTarefas) {
			if (tarefa.getCorCusto().equals("MAIOR")) {
				if (tarefa.getDataLimite().getMonth() == 0)
					jan += 1;
				if (tarefa.getDataLimite().getMonth() == 1)
					fev += 1;
				if (tarefa.getDataLimite().getMonth() == 2)
					mar += 1;
				if (tarefa.getDataLimite().getMonth() == 3)
					abr += 1;
				if (tarefa.getDataLimite().getMonth() == 4)
					mai += 1;
				if (tarefa.getDataLimite().getMonth() == 5)
					jun += 1;
				if (tarefa.getDataLimite().getMonth() == 6)
					julh += 1;
				if (tarefa.getDataLimite().getMonth() == 7)
					agos += 1;
				if (tarefa.getDataLimite().getMonth() == 8)
					sete += 1;
				if (tarefa.getDataLimite().getMonth() == 9)
					out += 1;
				if (tarefa.getDataLimite().getMonth() == 10)
					nov += 1;
				if (tarefa.getDataLimite().getMonth() == 11)
					dez += 1;
			}
		}
		charSeries.set("JAN", jan);
		charSeries.set("FEV", fev);
		charSeries.set("MAR", mar);
		charSeries.set("ABR", abr);
		charSeries.set("MAI", mai);
		charSeries.set("JUN", jun);
		charSeries.set("JUL", julh);
		charSeries.set("AGO", agos);
		charSeries.set("SET", sete);
		charSeries.set("OUT", out);
		charSeries.set("NOV", nov);
		charSeries.set("DEZ", dez);
		return charSeries;
	}

	@SuppressWarnings("deprecation")
	private ChartSeries getCustoBaixo() {
		ChartSeries charSeries = new ChartSeries();
		int jan = 0, fev = 0, mar = 0, abr = 0, mai = 0, jun = 0, julh = 0, agos = 0, sete = 0, out = 0, nov = 0,
				dez = 0;
		charSeries.setLabel("Custo Baixo");
		for (TarefaEntity tarefa : this.ListasTarefas) {
			if (tarefa.getCorCusto().equals("MENOR")) {
				if (tarefa.getDataLimite().getMonth() == 0)
					jan += 1;
				if (tarefa.getDataLimite().getMonth() == 1)
					fev += 1;
				if (tarefa.getDataLimite().getMonth() == 2)
					mar += 1;
				if (tarefa.getDataLimite().getMonth() == 3)
					abr += 1;
				if (tarefa.getDataLimite().getMonth() == 4)
					mai += 1;
				if (tarefa.getDataLimite().getMonth() == 5)
					jun += 1;
				if (tarefa.getDataLimite().getMonth() == 6)
					julh += 1;
				if (tarefa.getDataLimite().getMonth() == 7)
					agos += 1;
				if (tarefa.getDataLimite().getMonth() == 8)
					sete += 1;
				if (tarefa.getDataLimite().getMonth() == 9)
					out += 1;
				if (tarefa.getDataLimite().getMonth() == 10)
					nov += 1;
				if (tarefa.getDataLimite().getMonth() == 11)
					dez += 1;
			}
		}
		charSeries.set("JAN", jan);
		charSeries.set("FEV", fev);
		charSeries.set("MAR", mar);
		charSeries.set("ABR", abr);
		charSeries.set("MAI", mai);
		charSeries.set("JUN", jun);
		charSeries.set("JUL", julh);
		charSeries.set("AGO", agos);
		charSeries.set("SET", sete);
		charSeries.set("OUT", out);
		charSeries.set("NOV", nov);
		charSeries.set("DEZ", dez);
		return charSeries;
	}

	private void createBarModels() {
		createBarModel();
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Qtd.Situação no Ano");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Situação");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(10);
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public List<TarefaEntity> getListasTarefas() {
		return ListasTarefas;
	}

	public void setListasTarefas(List<TarefaEntity> listasTarefas) {
		ListasTarefas = listasTarefas;
	}

}
