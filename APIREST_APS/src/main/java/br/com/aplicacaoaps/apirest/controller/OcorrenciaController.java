package br.com.aplicacaoaps.apirest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aplicacaoaps.apirest.controller.dto.ComentarioDTO;
import br.com.aplicacaoaps.apirest.controller.dto.OcorrenciaDTO;
import br.com.aplicacaoaps.apirest.controller.dto.OcorrenciaListaDTO;
import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;
import br.com.aplicacaoaps.apirest.controller.form.AdicionaTecnicoForm;
import br.com.aplicacaoaps.apirest.controller.form.AtualizaStatusForm;
import br.com.aplicacaoaps.apirest.controller.form.ComentarioForm;
import br.com.aplicacaoaps.apirest.controller.form.OcorrenciaForm;
import br.com.aplicacaoaps.apirest.models.Comentarios;
import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;
import br.com.aplicacaoaps.apirest.repository.OcorrenciaRepository;
import br.com.aplicacaoaps.apirest.repository.TipoOcorrenciaRepository;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private TipoOcorrenciaRepository tipoOcorrenciaRepository;

	@GetMapping
	public ResponseEntity<List<OcorrenciaListaDTO>> buscaTodasOcorencia() {
		List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
		if (ocorrencias.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			List<OcorrenciaListaDTO> ocorrenciasListaDTO = new ArrayList<OcorrenciaListaDTO>();
			ocorrencias.forEach(ocorrencia -> {	ocorrenciasListaDTO.add(new OcorrenciaListaDTO(ocorrencia));});
			return ResponseEntity.ok(ocorrenciasListaDTO);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<OcorrenciaDTO> buscaOcorenciaPorId(@PathVariable Long id) {
		Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaRepository.findById(id);
		if (ocorrenciaOptional.isPresent()) {
			Ocorrencia ocorrencia = ocorrenciaOptional.get();
			UsuarioDTO autor = new UsuarioDTO(ocorrencia.getAutor());
			List<UsuarioDTO> tecnicos = ocorrencia.getTecnicos().stream().map(t -> new UsuarioDTO(t)).collect(Collectors.toList());
			List<ComentarioDTO> comentarios = ocorrencia.getComentarios().stream().map(c -> new ComentarioDTO(c)).collect(Collectors.toList());
			OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO(ocorrencia, autor, tecnicos, comentarios);
			return ResponseEntity.ok(ocorrenciaDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/comentarios")
	public List<ComentarioDTO> listarComentariosPorOcorrenciaId(@PathVariable Long id) {
		List<Comentarios> comentarios = comentarioRepository.findByComentarioPorOcorencia_id(id);
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

	@PostMapping
	public ResponseEntity<OcorrenciaDTO> gravarOcorrencia(@RequestBody @Valid OcorrenciaForm ocorrenciaForm, UriComponentsBuilder uriBuilder){
		if(ocorrenciaForm.getTipo().equals("VEICULO")) {
			Ocorrencia ocorrencia = ocorrenciaForm.converterTipoVeiculo(tipoOcorrenciaRepository);
			ocorrenciaRepository.save(ocorrencia);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(ocorrencia.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		if(ocorrenciaForm.getTipo().equals("REGIONAL")) {
			System.out.println(ocorrenciaForm.getTipo());
			Ocorrencia ocorrencia = ocorrenciaForm.converterTipoRegional(tipoOcorrenciaRepository);
			ocorrenciaRepository.save(ocorrencia);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(ocorrencia.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} else {
		return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{id}/adcionaTecnico")
	public ResponseEntity<?> adcionarTecnicoOcorrencia(@RequestBody @Valid List<AdicionaTecnicoForm> listaTecnico, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
		if(AdicionaTecnicoForm.adcionarNaOcorrencia(id, listaTecnico, ocorrenciaRepository)) {
			URI uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(uri).build();
		};
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/{id}/alteraStatus")
	public ResponseEntity<?> alteraStatus(@RequestBody @Valid AtualizaStatusForm atualizaStatus, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
		if(atualizaStatus.alteraStatus(id, ocorrenciaRepository)) {
			URI uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(uri).build();
		};
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/{id}/comentarios")
	public ResponseEntity<?> comentarioOcorrencia(@RequestBody @Valid ComentarioForm comentarioForm, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
		if(comentarioForm.adcionaComentario(id, comentarioRepository)) {
			URI uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(uri).build();
		};
		return ResponseEntity.badRequest().build();
	}

}
