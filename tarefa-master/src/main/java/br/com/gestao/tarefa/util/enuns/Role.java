package br.com.gestao.tarefa.util.enuns;

public enum Role {

	ADMIN("Admin"), ADMIN_COMMON("Admin_Comum"), COMMON("Comum");

	private String value;

	private Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}