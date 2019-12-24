package br.com.aplicacaoaps.apirest.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Classe concreta da "TipoChamado" para percistencia no banco, aqui contém todas as informações referentes a TipoChamadoRegional
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TipoChamadoRegional extends TipoChamado {

	private int cep;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<TagsRegional> tags;

	public TipoChamadoRegional() {}
	
	public TipoChamadoRegional(@Size(min = 8, max = 8) int cep,@NotNull List<TagsRegional> TagsRegional) {
		this.cep = cep;
		this.tags = TagsRegional;
		this.setTipo("REGIONAL");
	}

	public int getCep() {
		return cep;
	}

	public void setCEP(int cep) {
		this.cep = cep;
	}

	public List<TagsRegional> getTags() {
		return tags;
	}

	public void setTags(List<TagsRegional> tagsRegional) {
		this.tags = tagsRegional;
	}

}