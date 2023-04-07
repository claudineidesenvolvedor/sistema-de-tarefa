package br.com.gestao.tarefa.repository.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;


@Data
public class TarefaModel implements Serializable {

	private static final long serialVersionUID = 8222078747974991988L;

	private Integer id;

	private String nome;

	private BigDecimal custo = BigDecimal.ZERO;

	private Date dataLimite;
}
