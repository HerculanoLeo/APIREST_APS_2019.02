package br.com.aplicacaoaps.apirest.controller.form;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.aplicacaoaps.apirest.models.Perfil;
import br.com.aplicacaoaps.apirest.models.Usuario;

public class UsuarioForm {

	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	@Size(min = 6, max = 32)
	private String senha;
	private List<Perfil> perfil = Arrays.asList(new Perfil("ROLE_COMUM"));
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario converter() {
		return new Usuario(nome, email, senha, perfil);
	}


}
