package br.com.aplicacaoaps.apirest.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.aplicacaoaps.apirest.models.TipoUsuario;
import br.com.aplicacaoaps.apirest.models.Usuario;

public class UsuarioForm {

	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	@Size(min = 6, max = 32)
	private String senha;
	private TipoUsuario tipo = TipoUsuario.ROLE_COMUM;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setTipo(String tipo) {
		this.tipo = TipoUsuario.valueOf("ROLE_"+tipo);
	}
	
	public Usuario converter() {
		return new Usuario(nome, email, senha, tipo);
	}


}
