package br.com.gestao.tarefa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gestao.tarefa.repository.UsuariosRepository;
import br.com.gestao.tarefa.repository.entity.UsuarioEntity;
import br.com.gestao.tarefa.util.enuns.StatusPessoa;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuariosRepository usuarios;

	private UsuarioEntity filtro;

	private List<UsuarioEntity> usuarioFiltrado;

	public PesquisaUsuarioBean() {
		filtro = new UsuarioEntity();
	}

	public void pesquisar() {
		usuarioFiltrado = usuarios.filtrados(filtro);
	}

	public StatusPessoa[] getStatusPessoa() {
		return StatusPessoa.values();
	}

	public UsuarioEntity getFiltro() {
		return filtro;
	}

	public List<UsuarioEntity> getUsuarioFiltrado() {
		return usuarioFiltrado;
	}

	public void setUsuarioFiltrado(List<UsuarioEntity> usuarioFiltrado) {
		this.usuarioFiltrado = usuarioFiltrado;
	}
}
