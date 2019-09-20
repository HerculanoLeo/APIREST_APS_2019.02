package br.com.aplicacaoaps.apirest.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.aplicacaoaps.apirest.models.TipoUsuario;
import br.com.aplicacaoaps.apirest.models.Usuario;

public class AtualizaUsuarioForm {

	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	@NotBlank
	private String tipo;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario converter(Usuario usuaria) {
		usuaria.setEmail(this.email);
		usuaria.setNome(this.nome);
		usuaria.setTipoUsuario(TipoUsuario.valueOf(this.tipo));
		return usuaria;
	}

}
