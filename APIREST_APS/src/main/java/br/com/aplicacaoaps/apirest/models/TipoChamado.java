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
public abstract class TipoChamado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_Tipochamado")
	@SequenceGenerator(name = "SQ_Tipochamado", sequenceName = "SQ_Tipochamado", allocationSize = 1)
	private Long id;

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
