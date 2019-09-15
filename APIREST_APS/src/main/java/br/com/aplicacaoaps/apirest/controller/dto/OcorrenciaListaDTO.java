package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.models.TipoOcorrencia;

public class OcorrenciaListaDTO {
	private Long idOcorrencia;
	private TipoOcorrencia tipoOcorrencia;
	private Status status;
	private String titulo_Ocorrencia;
	private String descricao_Ocorrencia;
	private LocalDateTime dataAberturra, dataFechamento;

	public OcorrenciaListaDTO(Ocorrencia ocorrencia) {
		this.idOcorrencia = ocorrencia.getId();
		this.titulo_Ocorrencia = ocorrencia.getTitulo_Ocorrencia();
		this.descricao_Ocorrencia = ocorrencia.getDescricao_Ocorrencia();
		this.tipoOcorrencia = ocorrencia.getTipoOcorrencia();
		this.status = ocorrencia.getStatus();
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

}
