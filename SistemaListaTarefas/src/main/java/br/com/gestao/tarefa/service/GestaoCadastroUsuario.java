package br.com.gestao.tarefa.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.gestao.tarefa.repository.UsuariosRepository;
import br.com.gestao.tarefa.repository.entity.UsuarioEntity;
import br.com.gestao.tarefa.util.jpa.Transactional;

public class GestaoCadastroUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuariosRepository usuarios;

	@Transactional
	public UsuarioEntity salvar(UsuarioEntity entity) {
		//entity.setSenha(GenerateMD5.generate(entity.getSenha()));
		return usuarios.guardar(entity);
	}
}
