package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
/**
 * Classe concreta da "Tags" para percistencia no banco, aqui contém todas as informações referentes a TagsRegional
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TagsRegional extends Tags{

	private String nome;

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
