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
public class TipoChamadoVeiculo extends TipoChamado {

	@Column(name = "placa", length = 7)
	@Size(min = 7, max = 7) 
	private String placa;
	@ManyToMany(fetch = FetchType.EAGER)
	@NotNull 
	private List<TagsVeiculo> tags;
	
	public TipoChamadoVeiculo(){}
	
	public TipoChamadoVeiculo(String placa,List<TagsVeiculo> tagsVeiculo) {
		this.placa = placa;
		this.tags = tagsVeiculo;
		this.setTipo("VEICULO");
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
}
