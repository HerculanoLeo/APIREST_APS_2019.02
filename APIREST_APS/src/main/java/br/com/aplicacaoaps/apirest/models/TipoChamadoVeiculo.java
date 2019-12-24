package br.com.aplicacaoaps.apirest.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Classe concreta da "TipoChamado" para percistencia no banco, aqui contém todas as informações referentes a TipoChamadoVeiculo
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")  
public class TipoChamadoVeiculo extends TipoChamado {

	@Column(name = "placa", length = 7)
	private String placa;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<TagsVeiculo> tags;
	
	public TipoChamadoVeiculo(){}
	
	public TipoChamadoVeiculo(@Size(min = 7, max = 7) String placa, @NotNull List<TagsVeiculo> tagsVeiculo) {
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
