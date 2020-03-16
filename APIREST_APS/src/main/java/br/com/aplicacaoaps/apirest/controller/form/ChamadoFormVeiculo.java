package br.com.aplicacaoaps.apirest.controller.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.aplicacaoaps.apirest.controller.dto.TagsDTO;
import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.TipoChamado;
import br.com.aplicacaoaps.apirest.models.TipoChamadoVeiculo;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.TipoChamadoRepository;

public class ChamadoFormVeiculo {

	@NotNull
	private Long idAutor;
	@NotBlank
	private String titulo;
	@NotBlank
	private String descricao;
	@NotBlank
	@Size(min = 7, max = 7)
	private String placa;
	@NotNull
	private List<TagsDTO> tags;
	
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public List<TagsDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagsDTO> tags) {
		this.tags = tags;
	}

	public Chamado converterTipoVeiculo(TipoChamadoRepository tipoChamadoRepository) {
		Usuario usuario = new Usuario();
		usuario.setId(idAutor);
		TipoChamado tipoChamadoVeiculo = new TipoChamadoVeiculo(placa, TagsDTO.converteTagsVeiculo(tags));
		tipoChamadoRepository.save(tipoChamadoVeiculo);
		return new Chamado(usuario, titulo, descricao, (TipoChamadoVeiculo) tipoChamadoVeiculo);
	}

}
