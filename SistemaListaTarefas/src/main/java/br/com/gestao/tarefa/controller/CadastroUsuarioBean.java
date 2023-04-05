package br.com.gestao.tarefa.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gestao.tarefa.repository.entity.GrupoEntity;
import br.com.gestao.tarefa.repository.entity.UsuarioEntity;
import br.com.gestao.tarefa.service.GestaoCadastroUsuario;
import br.com.gestao.tarefa.util.enuns.StatusPessoa;
import br.com.gestao.tarefa.util.jsf.FacesUtil;

@SuppressWarnings("deprecation")
@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoCadastroUsuario usuarios;

	private UsuarioEntity usuario;

	private GrupoEntity grupo;

	public CadastroUsuarioBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {

		}
	}

	private void limpar() {
		this.usuario = new UsuarioEntity();
	}

	public void salvar() {

		this.usuario = usuarios.salvar(this.usuario);

		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");

	}

	public void addPerfil() {
		this.usuario.getGrupos().add(this.grupo);
		this.grupo.setUsuario(this.usuario);
	}

	public void novoPerfil() {
		this.grupo = new GrupoEntity();
	}

	public StatusPessoa[] getStatusPessoa() {
		return StatusPessoa.values();
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public GrupoEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEntity grupo) {
		this.grupo = grupo;
	}
}
