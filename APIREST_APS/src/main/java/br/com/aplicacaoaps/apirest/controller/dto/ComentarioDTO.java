package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;

import br.com.aplicacaoaps.apirest.models.Comentarios;

public class ComentarioDTO {

	private Long idComentario;
	private Long idAutor;
	private Long idOcorrencia;

	private String nomeAutor;
	private String emailAutor;

	private LocalDateTime dataCriacao;
	private String resposta;

	public ComentarioDTO(Comentarios comentarios) {
		this.idComentario = comentarios.getId();
		this.idAutor = comentarios.getAutor().getId();
		this.nomeAutor = comentarios.getAutor().getNome();
		this.emailAutor = comentarios.getAutor().getEmail();
		this.idOcorrencia = comentarios.getOcorrencia().getId();
		this.dataCriacao = comentarios.getDataCriacao();
		this.resposta = comentarios.getResposta();
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public String getEmailAutor() {
		return emailAutor;
	}

	public Long getIdComentario() {
		return idComentario;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	public Long getIdOcorrencia() {
		return idOcorrencia;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getResposta() {
		return resposta;
	}

}
