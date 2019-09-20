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

import br.com.aplicacaoaps.apirest.controller.dto.OcorrenciaDTO;
import br.com.aplicacaoaps.apirest.controller.dto.OcorrenciaListaDTO;
import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;
import br.com.aplicacaoaps.apirest.controller.form.AtualizaUsuarioForm;
import br.com.aplicacaoaps.apirest.controller.form.UsuarioForm;
import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.TipoUsuario;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.OcorrenciaRepository;
import br.com.aplicacaoaps.apirest.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@GetMapping
	public List<UsuarioDTO> listarUsuarios(){
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return usuariosDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscaUsuarioPorId(@PathVariable Long id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if(usuarioOptional.isPresent()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOptional.get());
			return ResponseEntity.ok(usuarioDTO);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/ocorrencia")
	public ResponseEntity<List<OcorrenciaListaDTO>> buscaOcorrenciaPoriDAutor(@PathVariable Long id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			List<Ocorrencia> listaOcorrencia = ocorrenciaRepository.findByAutor(usuario);
			List<OcorrenciaListaDTO> ocorrenciasListaDTO = new ArrayList<OcorrenciaListaDTO>();
			listaOcorrencia.forEach(ocorrencia -> {	ocorrenciasListaDTO.add(new OcorrenciaListaDTO(ocorrencia));});
			return ResponseEntity.ok(ocorrenciasListaDTO);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	
	@GetMapping("/tipo/{tipo}")
	public List<UsuarioDTO> buscarUsuarioPorTipo(@PathVariable String tipo){
		String tipoUpperCase = tipo.toUpperCase();
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioRepository.findBytipoUsuario(TipoUsuario.valueOf("ROLE_"+tipoUpperCase));
		usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return usuariosDTO;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastroUsuario(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioForm.converter();
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody @Valid AtualizaUsuarioForm atualizaUsuarioForm, @PathVariable Long id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if(usuarioOptional.isPresent()) {
		Usuario usuario = atualizaUsuarioForm.converter(usuarioOptional.get());
		usuarioRepository.save(usuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return ResponseEntity.ok(usuarioDTO);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	
}
