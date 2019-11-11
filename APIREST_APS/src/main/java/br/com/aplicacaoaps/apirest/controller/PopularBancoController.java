package br.com.aplicacaoaps.apirest.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.aplicacaoaps.apirest.infra.PopulaBanco;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;
import br.com.aplicacaoaps.apirest.repository.ChamadoRepository;
import br.com.aplicacaoaps.apirest.repository.PerfilRepository;
import br.com.aplicacaoaps.apirest.repository.TagsRegionalRepository;
import br.com.aplicacaoaps.apirest.repository.TagsVeiculoRepository;
import br.com.aplicacaoaps.apirest.repository.TipoChamadoRepository;
import br.com.aplicacaoaps.apirest.repository.UsuarioRepository;

@Controller
@RequestMapping("/popularBanco")
public class PopularBancoController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private TagsVeiculoRepository tagsVeiculoRepository;
	@Autowired
	private TagsRegionalRepository tagsRegionalRepository;
	@Autowired
	private TipoChamadoRepository tipoChamadoRepository;

	@GetMapping @Transactional @ResponseBody
	public String popular() {
		PopulaBanco.popular(usuarioRepository, chamadoRepository, comentarioRepository, tagsVeiculoRepository, tagsRegionalRepository, tipoChamadoRepository, perfilRepository);
		return "Foi";
	}
	
}
