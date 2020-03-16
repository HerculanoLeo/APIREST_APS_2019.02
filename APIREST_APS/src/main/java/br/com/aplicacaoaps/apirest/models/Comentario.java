package br.com.aplicacaoaps.apirest.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

	public Comentario(Usuario usuario, Chamado chamado, @NotBlank String comentario) {
		this.autor = usuario;
		this.chamado = chamado;
		this.resposta = comentario;
	}

}
