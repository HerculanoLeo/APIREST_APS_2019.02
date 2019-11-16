package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;

import br.com.aplicacaoaps.apirest.models.Comentario;
/**
 * Classe usada como modelo para enviar os dados de uma comentario do chamado, escondendo informações sensíveis, como por exemplo a senha do autor.
 *
 */
public class ComentarioDTO {

	private Long id_Comentario;
	private Long id_Autor;
	private Long id_Chamado;

	private String nomeAutor;
	private String emailAutor;

	private LocalDateTime dataCriacao;
	private String comentario;

	public ComentarioDTO(Comentario comentario) {
		this.id_Comentario = comentario.getId();
		this.id_Autor = comentario.getAutor().getId();
		this.nomeAutor = comentario.getAutor().getNome();
		this.emailAutor = comentario.getAutor().getEmail();
		this.id_Chamado = comentario.getChamado().getId();
		this.dataCriacao = comentario.getDataCriacao();
		this.comentario = comentario.getResposta();
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public String getEmailAutor() {
		return emailAutor;
	}

	public Long getId_Comentario() {
		return id_Comentario;
	}

	public Long getId_Autor() {
		return id_Autor;
	}

	public Long getId_Chamado() {
		return id_Chamado;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getResposta() {
		return comentario;
	}

}
