package br.com.aplicacaoaps.apirest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
/**
 * Classe abstrata para percistencia no banco, aqui contem a abstração das tags, dessa forma fica facilitado manipular todas as tegs
 *
 */
@Entity  
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Tags {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
}
