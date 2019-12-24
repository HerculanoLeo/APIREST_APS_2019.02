package br.com.aplicacaoaps.apirest.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.models.TipoChamado;

/**
 * Classe usada como modelo para enviar os dados do chamados, escondendo
 * informações sensíveis, como por exemplo a senha do autor
 * 
 */
public class ChamadoDTO {
	private Long id;
	private UsuarioDTO autor;
	private String titulo_Chamado;
	private String descricao_Chamado;
	private List<UsuarioDTO> tecnicos;
	private TipoChamado tipoChamado;
	private Status status;
	private List<ComentarioDTO> comentarios;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;

	public ChamadoDTO(Chamado chamado, UsuarioDTO autor, List<UsuarioDTO> tecnicos, List<ComentarioDTO> comentarios,
			TipoChamado tipoChamado) {
		this.id = chamado.getId();
		this.titulo_Chamado = chamado.getTitulo_Chamado();
		this.descricao_Chamado = chamado.getDescricao_Chamado();
		this.autor = autor;
		this.tecnicos = tecnicos;
		this.tipoChamado = tipoChamado;
		this.status = chamado.getStatus();
		this.comentarios = comentarios;
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
	}

	public Long getId() {
		return id;
	}

	public UsuarioDTO getAutor() {
		return autor;
	}

	public String getTitulo_Chamado() {
		return titulo_Chamado;
	}

	public String getDescricao_Chamado() {
		return descricao_Chamado;
	}

	public List<UsuarioDTO> getTecnicos() {
		return tecnicos;
	}

	public TipoChamado getTipoChamado() {
		return tipoChamado;
	}

	public Status getStatus() {
		return status;
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

}
