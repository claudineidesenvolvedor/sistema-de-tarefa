package br.com.gestao.tarefa.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.gestao.tarefa.repository.entity.UsuarioEntity;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private UsuarioEntity usuario;
	
	public UsuarioSistema(UsuarioEntity usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

}
