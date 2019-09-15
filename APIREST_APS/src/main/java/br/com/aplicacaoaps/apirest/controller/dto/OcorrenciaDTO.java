package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.models.TipoOcorrencia;

public class OcorrenciaDTO {
	private Long idOcorrencia;
	private UsuarioDTO autor;
	private String titulo_Ocorrencia;
	private String descricao_Ocorrencia;
	private List<UsuarioDTO> tecnicos;
	private TipoOcorrencia tipoOcorrencia;
	private Status status;
	private List<ComentarioDTO> comentarios;
	private LocalDateTime dataAberturra;
	private LocalDateTime dataFechamento;

	public OcorrenciaDTO(Ocorrencia ocorrencia, UsuarioDTO autor, List<UsuarioDTO> tecnicos,
			List<ComentarioDTO> comentarios) {
		this.idOcorrencia = ocorrencia.getId();
		this.titulo_Ocorrencia = ocorrencia.getTitulo_Ocorrencia();
		this.descricao_Ocorrencia = ocorrencia.getDescricao_Ocorrencia();
		this.autor = autor;
		this.tecnicos = tecnicos;
		this.tipoOcorrencia = ocorrencia.getTipoOcorrencia();
		this.status = ocorrencia.getStatus();
		this.comentarios = comentarios;
		this.dataAberturra = ocorrencia.getDataCriacao();
		this.dataFechamento = ocorrencia.getDataFechamento();
	}

	public Long getIdOcorrencia() {
		return idOcorrencia;
	}

	public String getTitulo_Ocorrencia() {
		return titulo_Ocorrencia;
	}

	public String getDescricao_Ocorrencia() {
		return descricao_Ocorrencia;
	}

	public UsuarioDTO getAutor() {
		return autor;
	}

	public List<UsuarioDTO> getTecnicos() {
		return tecnicos;
	}

	public LocalDateTime getDataAberturra() {
		return dataAberturra;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public Status getStatus() {
		return status;
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

}
