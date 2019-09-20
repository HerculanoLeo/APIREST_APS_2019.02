package br.com.aplicacaoaps.apirest.controller.dto;

import br.com.aplicacaoaps.apirest.models.TipoUsuario;
import br.com.aplicacaoaps.apirest.models.Usuario;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String email;
	private TipoUsuario tipo;

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.tipo = usuario.getTipoUsuario();
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

	public TipoUsuario getTipo() {
		return tipo;
	}

}
