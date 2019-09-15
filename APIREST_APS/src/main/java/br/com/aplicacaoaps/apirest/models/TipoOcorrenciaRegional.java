package br.com.aplicacaoaps.apirest.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TipoOcorrenciaRegional extends TipoOcorrencia {

	private String tipo = "REGIONAL";
	private Double longitude;
	private Double latitude;
	@ManyToMany
	private List<TagsRegional> tags;

	public TipoOcorrenciaRegional() {
		
	}
	
	public TipoOcorrenciaRegional(@NotNull Double latitude,@NotNull Double longitude,@NotNull List<TagsRegional> TagsRegional) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.tags = TagsRegional;
	}

	public int getId() {
		return this.id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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
