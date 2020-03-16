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

import lombok.Data;

@Data
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

	public Chamado() {}

	public Chamado(Usuario usuario, String titulo, String descricao,
			TipoChamadoVeiculo tipoOcorrenciaVeiculo) {
		this.autor = usuario;
		this.titulo_Chamado = titulo;
		this.descricao_Chamado = descricao;
		this.tipoChamado = tipoOcorrenciaVeiculo;
	}

	public Chamado(Usuario usuario, String titulo, String descricao,
			TipoChamadoRegional tipoChamadoRegional) {
		this.autor = usuario;
		this.titulo_Chamado = titulo;
		this.descricao_Chamado = descricao;
		this.tipoChamado = tipoChamadoRegional;
	}

}
