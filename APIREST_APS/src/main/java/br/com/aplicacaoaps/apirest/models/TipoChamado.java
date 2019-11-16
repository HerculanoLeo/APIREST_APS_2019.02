package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
/**
 * Classe abstrata para percistencia no banco, aqui contem a abstração do TipoChamado, dessa forma fica facilitado manipular todos os TipoChamados
 *
 */
@Entity  
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TipoChamado {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoChamado_sequence")
	@SequenceGenerator(name = "tipoChamado_sequence", sequenceName = "tipoChamado_sequence", allocationSize = 1)
	protected Long id;
	
	protected String tipo;

}
