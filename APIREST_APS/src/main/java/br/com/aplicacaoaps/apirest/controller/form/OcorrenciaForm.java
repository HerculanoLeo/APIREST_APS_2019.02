package br.com.aplicacaoaps.apirest.controller.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.TagsRegional;
import br.com.aplicacaoaps.apirest.models.TagsVeiculo;
import br.com.aplicacaoaps.apirest.models.TipoOcorrencia;
import br.com.aplicacaoaps.apirest.models.TipoOcorrenciaRegional;
import br.com.aplicacaoaps.apirest.models.TipoOcorrenciaVeiculo;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.TipoOcorrenciaRepository;

public class OcorrenciaForm {

	@NotNull
	private Long idAutor;
	@NotBlank
	private String titulo;
	@NotBlank
	private String descricao;
	@NotBlank
	private String tipo;
	private Double latitude=Double.NaN, longitude=Double.NaN;
	private String placa;
	private List<TagsRegional> TagsRegional;
	private List<TagsVeiculo> tagsVeiculo;
	
	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public List<TagsRegional> getTagsRegional() {
		return TagsRegional;
	}

	public void setTagsRegional(List<TagsRegional> tagsRegional) {
		TagsRegional = tagsRegional;
	}

	public List<TagsVeiculo> getTagsVeiculo() {
		return tagsVeiculo;
	}

	public void setTagsVeiculo(List<TagsVeiculo> tagsVeiculo) {
		this.tagsVeiculo = tagsVeiculo;
	}

	public Ocorrencia converterTipoVeiculo(TipoOcorrenciaRepository tipoOcorrenciaRepository) {
		Usuario usuario = new Usuario();
		usuario.setId(idAutor);
		TipoOcorrencia tipoOcorrenciaVeiculo = new TipoOcorrenciaVeiculo(placa, tagsVeiculo);
		tipoOcorrenciaRepository.save(tipoOcorrenciaVeiculo);
		return new Ocorrencia(usuario, titulo, descricao, (TipoOcorrenciaVeiculo) tipoOcorrenciaVeiculo);
	}
	
	public Ocorrencia converterTipoRegional(TipoOcorrenciaRepository tipoOcorrenciaRepository) {
		Usuario usuario = new Usuario();
		usuario.setId(idAutor);
		TipoOcorrencia tipoOcorrenciaRegional = new TipoOcorrenciaRegional(latitude, longitude, TagsRegional);
		tipoOcorrenciaRepository.save(tipoOcorrenciaRegional);
		return new Ocorrencia(usuario, titulo, descricao, (TipoOcorrenciaRegional) tipoOcorrenciaRegional);
	}
	
}
