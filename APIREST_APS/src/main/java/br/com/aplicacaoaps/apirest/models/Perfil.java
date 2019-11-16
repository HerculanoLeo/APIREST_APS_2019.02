package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
/**
 * Classe modelo para percistencia no banco, aqui contém todas as informações referentes referentes ao perfil(Niveis de Acesso)
 *
 */
@Entity
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id @Column(name = "nome", length = 50)
	private String nome;
	
	
	public Perfil() {}
	
	public Perfil(String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getAuthority() {
		return this.nome;
	}

}
