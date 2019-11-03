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
public class Ocorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo_Ocorrencia;
	private String descricao_Ocorrencia;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario autor;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Usuario> tecnicos;

	@OneToOne(fetch = FetchType.LAZY)
	private TipoOcorrencia tipoOcorrencia;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTO;

	@OneToMany(mappedBy = "ocorrencia", fetch = FetchType.LAZY)
	private List<Comentarios> comentarios = new ArrayList<Comentarios>();

	private LocalDateTime dataAbertura = LocalDateTime.now();
	private LocalDateTime dataFechamento;

	public Ocorrencia() {
	}

	public Ocorrencia(@NotNull Usuario usuario, @NotBlank String titulo, @NotBlank String descricao,
			@NotNull TipoOcorrenciaVeiculo tipoOcorrenciaVeiculo) {
		this.autor = usuario;
		this.titulo_Ocorrencia = titulo;
		this.descricao_Ocorrencia = descricao;
		this.tipoOcorrencia = tipoOcorrenciaVeiculo;
	}

	public Ocorrencia(@NotNull Usuario usuario, @NotBlank String titulo, @NotBlank String descricao,
			TipoOcorrenciaRegional tipoOcorrenciaRegional) {
		this.autor = usuario;
		this.titulo_Ocorrencia = titulo;
		this.descricao_Ocorrencia = descricao;
		this.tipoOcorrencia = tipoOcorrenciaRegional;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo_Ocorrencia() {
		return titulo_Ocorrencia;
	}

	public void setTitulo_Ocorrencia(String titulo_Ocorrencia) {
		this.titulo_Ocorrencia = titulo_Ocorrencia;
	}

	public String getDescricao_Ocorrencia() {
		return descricao_Ocorrencia;
	}

	public void setDescricao_Ocorrencia(String descricao_Ocorrencia) {
		this.descricao_Ocorrencia = descricao_Ocorrencia;
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

	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Comentarios> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentarios> comentarios) {
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
