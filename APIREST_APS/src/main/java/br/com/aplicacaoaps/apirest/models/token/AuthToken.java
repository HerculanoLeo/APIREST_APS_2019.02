package br.com.aplicacaoaps.apirest.models.token;

import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;

/**
 * Classe usada como modelo para enviar os dados do token
 * 
 */
public class AuthToken {

	private String tipo = "Bearer ";
	private String token;
	private UsuarioDTO usuario;

	public AuthToken() {
	}

	public AuthToken(String token, UsuarioDTO usuarioDTO) {
		this.token = token;
		this.usuario = usuarioDTO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuarioDTO) {
		this.usuario = usuarioDTO;
	}

}
