package br.com.aplicacaoaps.apirest.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.aplicacaoaps.apirest.models.TagsRegional;
import br.com.aplicacaoaps.apirest.models.TagsVeiculo;
import br.com.aplicacaoaps.apirest.repository.TagsRegionalRepository;
import br.com.aplicacaoaps.apirest.repository.TagsVeiculoRepository;

public class TagForm {

	@NotBlank
	private String nome;

	@Pattern(regexp = "^(veiculo|regional)$", message = "tipo: Deve ser veiculo ou regional")
	private String tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	} 
	
	public TagsRegional gravarTagRegional(TagsRegionalRepository tagsRegionalRepository) {
			TagsRegional tag = new TagsRegional();
			tag.setNome(this.nome);
			tagsRegionalRepository.save(tag);
			return tag;
	}
	public TagsVeiculo gravarTagVeiculo(TagsVeiculoRepository tagsVeiculoRepository) {
		TagsVeiculo tag = new TagsVeiculo();
		tag.setNome(this.nome);
		tagsVeiculoRepository.save(tag);
		return tag;
	}
	
	
}
