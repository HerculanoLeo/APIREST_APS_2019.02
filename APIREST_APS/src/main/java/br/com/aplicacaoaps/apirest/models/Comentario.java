package br.com.aplicacaoaps.apirest.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Classe modelo para percistencia no banco, aqui contém todas as informações referentes referentes ao comentario
 *
 */
@Entity
public class Comentario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario autor;
	
	@ManyToOne @JsonIgnore
	private Chamado chamado;
	private LocalDateTime dataCriacao = LocalDateTime.now();

	private String resposta;

	public Comentario() {}
	
	
	
	public Comentario(Usuario usuario, Chamado chamado, @NotBlank String comentario) {
		this.autor = usuario;
		this.chamado = chamado;
		this.resposta = comentario;
	}



	public Long getId() {
		return id;
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

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	
	
	
}
