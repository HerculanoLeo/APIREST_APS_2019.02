package br.com.aplicacaoaps.apirest.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Comentario;
import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;
/**
 * Classe usada para validar as informações recebidas pelo cliete referente a adição de um comentario no chamado
 * 
 */
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

	public Boolean adcionaComentario(Long idChamado, ComentarioRepository comentarioRepository) {
		try {
			Chamado chamado = new Chamado();
			chamado.setId(idChamado);
			Usuario usuario = new Usuario();
			usuario.setId(idAutor);
			Comentario comentario = new Comentario(usuario, chamado, this.comentario);
			comentarioRepository.save(comentario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
