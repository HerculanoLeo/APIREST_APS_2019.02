package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.models.TipoChamado;

public class ChamadoDTO {
	private Long id;
	private UsuarioDTO autor;
	private String titulo_Ocorrencia;
	private String descricao_Ocorrencia;
	private List<UsuarioDTO> tecnicos;
	private TipoChamado tipoOcorrencia;
	private Status status;
	private List<ComentarioDTO> comentarios;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;

	public ChamadoDTO(Chamado ocorrencia, UsuarioDTO autor, List<UsuarioDTO> tecnicos,
			List<ComentarioDTO> comentarios, TipoChamado tipoOcorrencia) {
		this.id = ocorrencia.getId();
		this.titulo_Ocorrencia = ocorrencia.getTitulo_Chamado();
		this.descricao_Ocorrencia = ocorrencia.getDescricao_Chamado();
		this.autor = autor;
		this.tecnicos = tecnicos;
		this.tipoOcorrencia = tipoOcorrencia;
		this.status = ocorrencia.getStatus();
		this.comentarios = comentarios;
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

	public UsuarioDTO getAutor() {
		return autor;
	}

	public List<UsuarioDTO> getTecnicos() {
		return tecnicos;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public TipoChamado getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public Status getStatus() {
		return status;
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

}
