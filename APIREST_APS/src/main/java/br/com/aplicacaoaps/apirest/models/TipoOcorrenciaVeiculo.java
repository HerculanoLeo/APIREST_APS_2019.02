package br.com.aplicacaoaps.apirest.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "id")  
public class TipoOcorrenciaVeiculo extends TipoOcorrencia {

	private String tipo = "VEICULO";
	@Column(name = "placa", length = 7)
	private String placa;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<TagsVeiculo> tags;
	
	public TipoOcorrenciaVeiculo(){
		
	}
	
	public TipoOcorrenciaVeiculo(@Size(min = 7, max = 7) String placa, @NotNull List<TagsVeiculo> tagsVeiculo) {
		this.placa = placa;
		this.tags = tagsVeiculo;
	}


	public Long getId() {
		return this.id;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public List<TagsVeiculo> getTags() {
		return tags;
	}

	public void setTags(List<TagsVeiculo> tagsVeiculo) {
		this.tags = tagsVeiculo;
	}

	public String getTipo() {
		return tipo;
	}

}
