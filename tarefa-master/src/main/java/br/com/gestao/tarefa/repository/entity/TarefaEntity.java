package br.com.gestao.tarefa.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.gestao.tarefa.util.jpa.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tarefa")
@SequenceGenerator(name = "Tarefa_Seq", sequenceName = "seq_tarefa", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "TarefaEntity.findAll", query = "SELECT p FROM TarefaEntity p"),
		@NamedQuery(name = "TarefaEntity.findAno", query = "SELECT p FROM TarefaEntity p where p.dataLimite BETWEEN :dataCriacaoDe and :dataCriacaoAte ") })
public class TarefaEntity implements Serializable {

	private static final long serialVersionUID = 8222078747974991988L;
	
	@Id
	@Column(name = "tarefaid", nullable = false, unique = true)
	@PrimaryKeyJoinColumn(name = "tarefaPK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Tarefa_Seq")
	private Integer id;

	@Column(nullable = false, length = 100, name = "nome")
	private String nome;

	@Column(name = "custo", nullable = false, precision = 10, scale = 2)
	private BigDecimal custo = BigDecimal.ZERO;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_Limite", nullable = false)
	private Date dataLimite;

	@Transactional
	public String getCorCusto() {
		String cor = "";
		try {
			DecimalFormat df = new DecimalFormat("###,##0", new DecimalFormatSymbols(new Locale("pt", "BR")));
			df.setParseBigDecimal(true);
			BigDecimal decimal = (BigDecimal) df.parse("1.000");
			if (this.custo.compareTo(decimal) == 1 || this.custo.compareTo(decimal) == 0)
				cor = "MAIOR";
			else
				cor = "MENOR";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cor;

	}
}
