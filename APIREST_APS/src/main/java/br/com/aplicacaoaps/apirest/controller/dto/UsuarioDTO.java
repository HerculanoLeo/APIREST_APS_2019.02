package br.com.aplicacaoaps.apirest.controller.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import br.com.aplicacaoaps.apirest.models.Usuario;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String email;
	private Collection<? extends GrantedAuthority> perfil;

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.perfil = usuario.getAuthorities();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Collection<? extends GrantedAuthority> getPerfil() {
		return perfil;
	}

}
