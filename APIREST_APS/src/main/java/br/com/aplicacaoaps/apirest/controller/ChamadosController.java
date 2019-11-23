package br.com.aplicacaoaps.apirest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aplicacaoaps.apirest.controller.dto.ChamadoDTO;
import br.com.aplicacaoaps.apirest.controller.dto.ChamadoListaDTO;
import br.com.aplicacaoaps.apirest.controller.dto.ComentarioDTO;
import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;
import br.com.aplicacaoaps.apirest.controller.form.AdicionaTecnicoForm;
import br.com.aplicacaoaps.apirest.controller.form.AtualizaStatusForm;
import br.com.aplicacaoaps.apirest.controller.form.ChamadoForm;
import br.com.aplicacaoaps.apirest.controller.form.ComentarioForm;
import br.com.aplicacaoaps.apirest.controller.form.TagForm;
import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Comentario;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.models.Tags;
import br.com.aplicacaoaps.apirest.models.TagsRegional;
import br.com.aplicacaoaps.apirest.models.TagsVeiculo;
import br.com.aplicacaoaps.apirest.models.TipoChamado;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.ChamadoRepository;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;
import br.com.aplicacaoaps.apirest.repository.TagsRegionalRepository;
import br.com.aplicacaoaps.apirest.repository.TagsRepository;
import br.com.aplicacaoaps.apirest.repository.TagsVeiculoRepository;
import br.com.aplicacaoaps.apirest.repository.TipoChamadoRepository;

/**
 * Classe controller para os chamados, aqui tem os endpoints para acessar as
 * informações referentes aos chamados EndPoint: /chamado/**
 */
@RestController
@RequestMapping("/chamado")
public class ChamadosController {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private TipoChamadoRepository tipoChamadoRepository;
	@Autowired
	private TagsVeiculoRepository tagsVeiculoRepository;
	@Autowired
	private TagsRegionalRepository tagsRegionalRepository;
	@Autowired
	private TagsRepository tagsRepository;

	/**
	 * Retorna json com lista paginada de todos os Chamados
	 * 
	 */
//	@PreAuthorize("hasAnyRole('TECNICO', 'GERENTE')")
	@GetMapping
	public ResponseEntity<Page<ChamadoListaDTO>> buscarTodosChamados(Pageable pageable) {
		Page<Chamado> chamados = chamadoRepository.findAll(pageable);
		if (chamados.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(ChamadoListaDTO.converter(chamados));
		}
	}

	/**
	 * Retorna json com lista paginada de todos os Chamados filtrado pelo status e autor(opicional)
	 * exemplo do endpoint: http://localhost:8080/chamado/status/aberto?autor=1&&page=0&&size=10&&sort=dataAbertura,id,desc
	 */
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@GetMapping("/status/{status}")
	public ResponseEntity<Page<ChamadoListaDTO>> buscarChamadoPorAutor_Status(@PathVariable String status,
			Pageable pageable, @RequestParam(required = false, value = "autor") Long autor) {
		System.out.println(autor);
		String statusUPPER = status.toUpperCase();
		Page<Chamado> chamados;
		if (autor == null) {
			chamados = chamadoRepository.findByStatus(Status.valueOf(statusUPPER), pageable);
		} else {
			Usuario usuario = new Usuario();
			usuario.setId(autor);
			chamados = chamadoRepository.findByAutorAndStatus(usuario, Status.valueOf(statusUPPER), pageable);
		}
		if (chamados.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(ChamadoListaDTO.converter(chamados));
		}
	}

	/**
	 * Retorna json com lista paginada de todos os Chamados do tecnico especifio pelo seu id e pelo status
	 * Exemplo do endpoint: http://localhost:8080/chamado/tecnico/2/aberto?page=0&&size=100&&sort=dataAbertura,id,desc
	 */
//	@PreAuthorize("hasAnyRole('TECNICO', 'GERENTE')")
	@GetMapping("/tecnico/{id}/{status}")
	public ResponseEntity<Page<ChamadoListaDTO>> buscarChamadoPorTecnico_Status(@PathVariable Long id,
			@PathVariable String status, Pageable pageable) {
		String statusUPPER = status.toUpperCase();
		Usuario usuario = new Usuario();
		usuario.setId(id);
		List<Usuario> tecnicos = Arrays.asList(usuario);
		Page<Chamado> chamados = chamadoRepository.findByTecnicosAndStatus(tecnicos, Status.valueOf(statusUPPER),
				pageable);
		if (chamados.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(ChamadoListaDTO.converter(chamados));
		}
	}

	/**
	 * Retorno detalhado do chamado especificado pelo seu id
	 * 
	 */
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@GetMapping("/{id}")
	public ResponseEntity<ChamadoDTO> buscaChamadoPorId(@PathVariable Long id) {
		Optional<Chamado> chamadoOptional = chamadoRepository.findById(id);
		if (chamadoOptional.isPresent()) {
			Optional<TipoChamado> tipoChamadoOptional = tipoChamadoRepository.findById(id);
			Chamado chamado = chamadoOptional.get();
			chamado.setTipoChamado(null);
			UsuarioDTO autor = new UsuarioDTO(chamado.getAutor());
			List<UsuarioDTO> tecnicos = chamado.getTecnicos().stream().map(t -> new UsuarioDTO(t)).collect(Collectors.toList());
			List<ComentarioDTO> comentarios = chamado.getComentarios().stream().map(c -> new ComentarioDTO(c)).collect(Collectors.toList());
			TipoChamado tipoChamado = tipoChamadoOptional.get();
			ChamadoDTO chamadoDTO = new ChamadoDTO(chamado, autor, tecnicos, comentarios, tipoChamado);
			return ResponseEntity.ok(chamadoDTO);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Retorno dos comentarios do chamado especificado pelo seu id
	 * 
	 */
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@GetMapping("/{id}/comentarios")
	public List<ComentarioDTO> listarComentariosPorChamadoId(@PathVariable Long id) {
		List<Comentario> comentarios = comentarioRepository.findByComentarioPorChamado_id(id);
		if (comentarios.isEmpty()) {
			return null;
		} else {
			List<ComentarioDTO> comentariosDTO = new ArrayList<ComentarioDTO>();
			comentarios.forEach(c -> {
				comentariosDTO.add(new ComentarioDTO(c));
			});
			return comentariosDTO;
		}
	}

	/**
	 * Gravar um novo chamado
	 * {
			"idAutor": "1",
			"titulo" : "teSTE1",
			"descricao": "descriçao",
			"tipo" : "VEICULO",
			"placa" : "xxx7777",
			"tagsVeiculo":[
				{
					"id":"1"
				},
				{
					"id":"2"
				}
			]

		}
		ou
		{
			"idAutor": "1",
			"titulo" : "teSTE1",
			"descricao": "descriçao",
			"tipo" : "REGIONAL",
			"cep" : "13130130",
			"tagsRegional":[
				{
					"id":"1"
				},
				{
					"id":"2"
				}
			]
		
		}
	 * 
	 */
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@PostMapping
	public ResponseEntity<ChamadoDTO> gravarChamado(@RequestBody @Valid ChamadoForm chamadoForm,
			UriComponentsBuilder uriBuilder) {
		if (chamadoForm.getTipo().equals("VEICULO")) {
			Chamado chamado = chamadoForm.converterTipoVeiculo(tipoChamadoRepository);
			chamadoRepository.save(chamado);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(chamado.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		if (chamadoForm.getTipo().equals("REGIONAL")) {
			System.out.println(chamadoForm.getTipo());
			Chamado ocorrencia = chamadoForm.converterTipoRegional(tipoChamadoRepository);
			chamadoRepository.save(ocorrencia);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(ocorrencia.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Adicionar tecnico ao chamado
	 * [
			{
				“idTecnico”: “”
			},
			{
				“idTecnico”: “”
			}
		]

	 * 
	 */
//	@PreAuthorize("hasAnyRole('GERENTE')")
	@PutMapping("/{id}/adicionarTecnico")
	public ResponseEntity<?> adicionarTecnicosChamado(@RequestBody @Valid List<AdicionaTecnicoForm> listaTecnico,
			@PathVariable Long id, UriComponentsBuilder uriBuilder) {
		if (AdicionaTecnicoForm.adcionarNoChamado(id, listaTecnico, chamadoRepository)) {
			URI uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * Alterar o status chamado referente ao id
	 * 
	 * {
			status” : “FECHADO” (ou outro).

		}
	 * 
	 */
//	@PreAuthorize("hasAnyRole('TECNICO', 'GERENTE')")
	@PutMapping("/{id}/alteraStatus")
	public ResponseEntity<?> alteraStatus(@RequestBody @Valid AtualizaStatusForm atualizaStatus, @PathVariable Long id,
			UriComponentsBuilder uriBuilder) {
		if (atualizaStatus.alteraStatus(id, chamadoRepository)) {
			URI uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * adicionar comomentario a chamado expecificado pelo id
	 * {
			"idAutor":"1",
			"comentario":"Teste atualização do comentario"
		}
	 * 
	 */
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@PutMapping("/{id}/comentarios")
	public ResponseEntity<?> adcionarComentario(@RequestBody @Valid ComentarioForm comentarioForm,
			@PathVariable Long id, UriComponentsBuilder uriBuilder) {
		if (comentarioForm.adcionaComentario(id, comentarioRepository)) {
			URI uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * {
			"nome" : "Vazamento de Oleo",
			"tipo" : "regional" ou "veiculo"
		}
	 * 
	 */
//	@PreAuthorize("hasAnyRole('GERENTE')")
	@PostMapping("/tags")
	public ResponseEntity<?> gravarTags(@RequestBody @Valid TagForm tagForm, UriComponentsBuilder uriBuilder) {
		if (tagForm.getTipo().equals("veiculo")) {
			TagsVeiculo tag = tagForm.gravarTagVeiculo(tagsVeiculoRepository);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(tag.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		if (tagForm.getTipo().equals("regional")) {
			TagsRegional tag = tagForm.gravarTagRegional(tagsRegionalRepository);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(tag.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	/**
	 * apaga a tag de acordo com o id
	 * 
	 */
//	@PreAuthorize("hasAnyRole('GERENTE')")
	@DeleteMapping("/tags/{id}")
	public ResponseEntity<?> deletarTags(@PathVariable Long id) {
		Optional<Tags> optional = tagsRepository.findById(id);
		if (optional.isPresent()) {
			tagsRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Lista todas as tag Regional
	 * 
	 */
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@GetMapping("/tags/regional")
	public ResponseEntity<?> listarTagsRegional() {
		List<TagsRegional> listaTags = tagsRegionalRepository.findAll();
		if (listaTags.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(listaTags);
		}
	}

	/**
	 * Lista todas as tag Veiculo
	 * 
	 */
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@GetMapping("/tags/veiculo")
	public ResponseEntity<?> listarTagsVeiculo() {
		List<TagsVeiculo> listaTags = tagsVeiculoRepository.findAll();
		if (listaTags.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(listaTags);
		}
	}

}
