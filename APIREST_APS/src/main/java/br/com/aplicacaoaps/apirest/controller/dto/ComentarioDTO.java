package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;

import br.com.aplicacaoaps.apirest.models.Comentarios;

public class ComentarioDTO {

	private Long id_Comentario;
	private Long id_Autor;
	private Long id_Ocorrencia;

	private String nomeAutor;
	private String emailAutor;

	private LocalDateTime dataCriacao;
	private String resposta;

	public ComentarioDTO(Comentarios comentarios) {
		this.id_Comentario = comentarios.getId();
		this.id_Autor = comentarios.getAutor().getId();
		this.nomeAutor = comentarios.getAutor().getNome();
		this.emailAutor = comentarios.getAutor().getEmail();
		this.id_Ocorrencia = comentarios.getOcorrencia().getId();
		this.dataCriacao = comentarios.getDataCriacao();
		this.resposta = comentarios.getResposta();
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

	public Long getId_Ocorrencia() {
		return id_Ocorrencia;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getResposta() {
		return resposta;
	}

}
