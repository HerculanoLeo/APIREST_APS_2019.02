package br.com.aplicacaoaps.apirest.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.aplicacaoaps.apirest.infra.PopulaTabela;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;
import br.com.aplicacaoaps.apirest.repository.OcorrenciaRepository;
import br.com.aplicacaoaps.apirest.repository.PerfilRepository;
import br.com.aplicacaoaps.apirest.repository.TagsRegionalRepository;
import br.com.aplicacaoaps.apirest.repository.TagsVeiculoRepository;
import br.com.aplicacaoaps.apirest.repository.TipoOcorrenciaRepository;
import br.com.aplicacaoaps.apirest.repository.UsuarioRepository;

@Controller
@RequestMapping("/popularBanco")
public class PopularBancoController {
	@Autowired
	private UsuarioRepository usuaarioRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private TagsVeiculoRepository tagsVeiculoRepository;
	@Autowired
	private TagsRegionalRepository tagsRegionalRepository;
	@Autowired
	private TipoOcorrenciaRepository tipoOcorrenciaRepository;

	@GetMapping @Transactional @ResponseBody
	public String popular() {
		PopulaTabela.populaUsuario(usuaarioRepository, ocorrenciaRepository, comentarioRepository, tagsVeiculoRepository, tagsRegionalRepository, tipoOcorrenciaRepository, perfilRepository);
		return "Foi";
	}
	
}
