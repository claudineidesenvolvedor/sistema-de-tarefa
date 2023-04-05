package br.com.gestao.tarefa.util.enuns;

public enum TipoPessoaGrupo {
	ASSOCIADO("Contribuinte Associado"), DEPENDENTE("Dependente");	

	private String value;

	private TipoPessoaGrupo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
