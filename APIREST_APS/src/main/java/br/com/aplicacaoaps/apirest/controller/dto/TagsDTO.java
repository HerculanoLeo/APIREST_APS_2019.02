package br.com.aplicacaoaps.apirest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aplicacaoaps.apirest.models.TagsRegional;
import br.com.aplicacaoaps.apirest.models.TagsVeiculo;

public class TagsDTO {
	private Long id;
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<TagsVeiculo> converteTagsVeiculo(List<TagsDTO> tags) {
		return tags.stream().map(t -> new TagsVeiculo(t.getId(), t.getNome())).collect(Collectors.toList());
	}

	public static List<TagsRegional> converteTagsRegional(List<TagsDTO> tags) {
		return tags.stream().map(t -> new TagsRegional(t.getId(), t.getNome())).collect(Collectors.toList());
	}
}
