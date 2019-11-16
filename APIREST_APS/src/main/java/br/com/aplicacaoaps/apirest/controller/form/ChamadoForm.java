package br.com.aplicacaoaps.apirest.controller.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.TagsRegional;
import br.com.aplicacaoaps.apirest.models.TagsVeiculo;
import br.com.aplicacaoaps.apirest.models.TipoChamado;
import br.com.aplicacaoaps.apirest.models.TipoChamadoRegional;
import br.com.aplicacaoaps.apirest.models.TipoChamadoVeiculo;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.TipoChamadoRepository;
/**
 * Classe usada para validar as informações recebidas pelo cliete referente a criação de um novo chamado
 * 
 */
public class ChamadoForm {

	@NotNull
	private Long idAutor;
	@NotBlank
	private String titulo;
	@NotBlank
	private String descricao;
	@NotBlank
	private String tipo;
	private int cep;
	private String placa;
	private List<TagsRegional> tagsRegional;
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

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public List<TagsRegional> getTagsRegional() {
		return tagsRegional;
	}

	public void setTagsRegional(List<TagsRegional> tagsRegional) {
		this.tagsRegional = tagsRegional;
	}

	public List<TagsVeiculo> getTagsVeiculo() {
		return tagsVeiculo;
	}

	public void setTagsVeiculo(List<TagsVeiculo> tagsVeiculo) {
		this.tagsVeiculo = tagsVeiculo;
	}

	public Chamado converterTipoVeiculo(TipoChamadoRepository tipoChamadoRepository) {
		Usuario usuario = new Usuario();
		usuario.setId(idAutor);
		TipoChamado tipoChamadoVeiculo = new TipoChamadoVeiculo(placa, tagsVeiculo);
		tipoChamadoRepository.save(tipoChamadoVeiculo);
		return new Chamado(usuario, titulo, descricao, (TipoChamadoVeiculo) tipoChamadoVeiculo);
	}
	
	public Chamado converterTipoRegional(TipoChamadoRepository tipoChamadoRepository) {
		Usuario usuario = new Usuario();
		usuario.setId(idAutor);
		TipoChamado tipoChamadoRegional = new TipoChamadoRegional(cep, tagsRegional);
		tipoChamadoRepository.save(tipoChamadoRegional);
		return new Chamado(usuario, titulo, descricao, (TipoChamadoRegional) tipoChamadoRegional);
	}
	
}
