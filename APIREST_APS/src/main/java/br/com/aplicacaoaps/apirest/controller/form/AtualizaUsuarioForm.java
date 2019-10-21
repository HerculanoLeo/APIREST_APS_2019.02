package br.com.aplicacaoaps.apirest.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.aplicacaoaps.apirest.models.Perfil;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.PerfilRepository;

public class AtualizaUsuarioForm {

	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	@NotBlank
	private String perfil;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTipo(String perfil) {
		this.perfil = perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Usuario converter(Usuario usuaria, PerfilRepository perfilRepository) {
		usuaria.setEmail(this.email);
		usuaria.setNome(this.nome);
		Optional<Perfil>perfilOptional = perfilRepository.findById(perfil);
		List<Perfil> listPerfil = new ArrayList<Perfil>();
		listPerfil.add(perfilOptional.get());
		usuaria.setPerfil(listPerfil);
		return usuaria;
	}

}
