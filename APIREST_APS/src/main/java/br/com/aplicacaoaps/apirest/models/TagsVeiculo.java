package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id") 
public class TagsVeiculo extends Tags {

	public TagsVeiculo(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
