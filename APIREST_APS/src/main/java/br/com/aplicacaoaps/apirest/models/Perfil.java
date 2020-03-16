package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id @Column(name = "nome", length = 50) @JsonIgnore
	private String nome;
	
	public Perfil(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
