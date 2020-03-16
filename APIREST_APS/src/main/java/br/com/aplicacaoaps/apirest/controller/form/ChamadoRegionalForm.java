package br.com.aplicacaoaps.apirest.controller.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.aplicacaoaps.apirest.controller.dto.TagsDTO;
import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.TipoChamado;
import br.com.aplicacaoaps.apirest.models.TipoChamadoRegional;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.TipoChamadoRepository;

public class ChamadoRegionalForm {

	@NotNull
	private Long idAutor;
	@NotBlank
	private String titulo;
	@NotBlank
	private String descricao;
	@NotNull
	@Size(min = 8, max = 8)
	private int cep;
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

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public List<TagsDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagsDTO> tags) {
		this.tags = tags;
	}

	public Chamado converterTipoRegional(TipoChamadoRepository tipoChamadoRepository) {
		Usuario usuario = new Usuario();
		usuario.setId(idAutor);
		TipoChamado tipoChamadoRegional = new TipoChamadoRegional(cep, TagsDTO.converteTagsRegional(tags));
		tipoChamadoRepository.save(tipoChamadoRegional);
		return new Chamado(usuario, titulo, descricao, (TipoChamadoRegional) tipoChamadoRegional);
	}
}
