package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Status;

public class ChamadoListaDTO {
	private Long id;
	private Status status;
	private String titulo_Chamado;
	private String descricao_Chamado;
	private LocalDateTime dataAbertura, dataFechamento;

	public ChamadoListaDTO(Chamado chamado) {
		this.id = chamado.getId();
		this.titulo_Chamado = chamado.getTitulo_Chamado();
		this.descricao_Chamado = chamado.getDescricao_Chamado();
		this.status = chamado.getStatus();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo_Chamado() {
		return titulo_Chamado;
	}

	public String getDescricao_Chamado() {
		return descricao_Chamado;
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
	
	public static Page<ChamadoListaDTO> converter(Page<Chamado> chamados){
		return chamados.map(ChamadoListaDTO::new);
	}

}
