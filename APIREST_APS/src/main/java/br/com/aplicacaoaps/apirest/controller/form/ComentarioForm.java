package br.com.aplicacaoaps.apirest.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Comentarios;
import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;

public class ComentarioForm {

	@NotNull
	private Long idAutor;
	@NotBlank
	private String comentario;

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean adcionaComentario(Long idOcorrencia, ComentarioRepository comentarioRepository) {
		try {
			Ocorrencia ocorrencia = new Ocorrencia();
			ocorrencia.setId(idOcorrencia);
			Usuario usuario = new Usuario();
			usuario.setId(idAutor);
			Comentarios comentario = new Comentarios(usuario, ocorrencia, this.comentario);
			comentarioRepository.save(comentario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
