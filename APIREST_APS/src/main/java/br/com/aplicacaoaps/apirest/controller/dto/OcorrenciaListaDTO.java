package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Status;

public class OcorrenciaListaDTO {
	private Long id;
	private Status status;
	private String titulo_Ocorrencia;
	private String descricao_Ocorrencia;
	private LocalDateTime dataAbertura, dataFechamento;

	public OcorrenciaListaDTO(Ocorrencia ocorrencia) {
		this.id = ocorrencia.getId();
		this.titulo_Ocorrencia = ocorrencia.getTitulo_Ocorrencia();
		this.descricao_Ocorrencia = ocorrencia.getDescricao_Ocorrencia();
		this.status = ocorrencia.getStatus();
		this.dataAbertura = ocorrencia.getDataAbertura();
		this.dataFechamento = ocorrencia.getDataFechamento();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo_Ocorrencia() {
		return titulo_Ocorrencia;
	}

	public String getDescricao_Ocorrencia() {
		return descricao_Ocorrencia;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public Status getStatus() {
		return status;
	}
	
	public static Page<OcorrenciaListaDTO> converter(Page<Ocorrencia> ocorrecias){
		return ocorrecias.map(OcorrenciaListaDTO::new);
	}

}
