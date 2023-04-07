package br.com.gestao.tarefa.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.gestao.tarefa.repository.UsuariosRepository;
import br.com.gestao.tarefa.repository.entity.GrupoEntity;
import br.com.gestao.tarefa.repository.entity.UsuarioEntity;
import br.com.gestao.tarefa.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuariosRepository usuarios = CDIServiceLocator.getBean(UsuariosRepository.class);
		UsuarioEntity usuario = usuarios.porEmail(email);
		
		UsuarioSistema user = null;
		
		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(UsuarioEntity usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (GrupoEntity grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getDescricao().getValue().toUpperCase()));
		}
		
		return authorities;
	}

}
