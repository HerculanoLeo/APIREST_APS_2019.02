package br.com.aplicacaoaps.apirest.controller.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import br.com.aplicacaoaps.apirest.models.Usuario;
/**
 * Classe usada como modelo para enviar os dados do usuario, escondendo informações sensíveis, como por exemplo a sua senha
 * 
 */
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

	public static List<UsuarioDTO> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
	}

}
