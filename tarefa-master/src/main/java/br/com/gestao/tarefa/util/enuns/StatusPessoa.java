package br.com.gestao.tarefa.util.enuns;

public enum StatusPessoa {
	
	ATIVO("Ativo"), SUSPENSO("Suspenso"), CANCELADO("Cancelado"), INATIVO("Inativo");

	private String descricao;

	StatusPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
