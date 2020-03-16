package br.com.aplicacaoaps.apirest.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TipoChamadoRegional extends TipoChamado {

	private int cep;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<TagsRegional> tags;

	public TipoChamadoRegional() {}
	
	public TipoChamadoRegional(int cep,List<TagsRegional> TagsRegional) {
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