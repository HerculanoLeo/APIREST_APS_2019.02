package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity  
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TipoOcorrencia {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

}
