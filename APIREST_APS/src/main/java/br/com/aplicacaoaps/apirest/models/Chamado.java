package br.com.aplicacaoaps.apirest.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Chamado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo_Chamado;
	private String descricao_Chamado;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario autor;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Usuario> tecnicos;

	@OneToOne(fetch = FetchType.LAZY)
	private TipoChamado tipoChamado;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTO;

	@OneToMany(mappedBy = "chamado", fetch = FetchType.LAZY)
	private List<Comentario> comentarios = new ArrayList<Comentario>();

	private LocalDateTime dataAbertura = LocalDateTime.now();
	private LocalDateTime dataFechamento;

	public Chamado() {
	}

	public Chamado(@NotNull Usuario usuario, @NotBlank String titulo, @NotBlank String descricao,
			@NotNull TipoChamadoVeiculo tipoOcorrenciaVeiculo) {
		this.autor = usuario;
		this.titulo_Chamado = titulo;
		this.descricao_Chamado = descricao;
		this.tipoChamado = tipoOcorrenciaVeiculo;
	}

	public Chamado(@NotNull Usuario usuario, @NotBlank String titulo, @NotBlank String descricao,
			TipoChamadoRegional tipoChamadoRegional) {
		this.autor = usuario;
		this.titulo_Chamado = titulo;
		this.descricao_Chamado = descricao;
		this.tipoChamado = tipoChamadoRegional;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo_Chamado() {
		return titulo_Chamado;
	}

	public void setTitulo_Chamado(String titulo_Chamado) {
		this.titulo_Chamado = titulo_Chamado;
	}

	public String getDescricao_Chamado() {
		return descricao_Chamado;
	}

	public void setDescricao_Chamado(String descricao_Chamado) {
		this.descricao_Chamado = descricao_Chamado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public List<Usuario> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Usuario> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public TipoChamado getTipoChamado() {
		return tipoChamado;
	}

	public void setTipoChamado(TipoChamado tipoChamado) {
		this.tipoChamado = tipoChamado;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

}
