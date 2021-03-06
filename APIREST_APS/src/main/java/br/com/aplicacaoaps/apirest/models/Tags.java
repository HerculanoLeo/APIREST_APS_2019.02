package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity  
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Tags {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_Tags")
	@SequenceGenerator(name = "SQ_Tags", sequenceName = "SQ_Tags", allocationSize = 1)
	protected Long id;
	protected String nome;
	
	public abstract void setId(Long id);
	public abstract Long getId();

	public abstract void setNome(String nome);
	public abstract String getNome();
}
