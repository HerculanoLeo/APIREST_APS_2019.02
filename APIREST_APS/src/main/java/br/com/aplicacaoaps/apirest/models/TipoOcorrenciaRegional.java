package br.com.aplicacaoaps.apirest.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TipoOcorrenciaRegional extends TipoOcorrencia {

	private String tipo = "REGIONAL";
	private int cep;
	@ManyToMany
	private List<TagsRegional> tags;

	public TipoOcorrenciaRegional() {
		
	}
	
	public TipoOcorrenciaRegional(@Size(min = 8, max = 8) int cep,@NotNull List<TagsRegional> TagsRegional) {
		this.cep = cep;
		this.tags = TagsRegional;
	}

	public int getId() {
		return this.id;
	}

	public double getCep() {
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

	public String getTipo() {
		return this.tipo;
	}

	
}
