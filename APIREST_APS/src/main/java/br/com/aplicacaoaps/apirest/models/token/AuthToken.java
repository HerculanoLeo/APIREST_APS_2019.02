package br.com.aplicacaoaps.apirest.models.token;

import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;
import lombok.Data;

@Data
public class AuthToken {

	private String tipo = "Bearer ";
	private String token;
	private UsuarioDTO usuario;

	public AuthToken(String token, UsuarioDTO usuarioDTO) {
		this.token = token;
		this.usuario = usuarioDTO;
	}

}
