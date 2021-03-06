package br.com.aplicacaoaps.apirest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;
import br.com.aplicacaoaps.apirest.controller.form.UsuarioForm;
import br.com.aplicacaoaps.apirest.models.Perfil;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.PerfilRepository;
import br.com.aplicacaoaps.apirest.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
//	@Autowired
//	private ChamadoRepository chamadoRepository;
	@Autowired
	private PerfilRepository perfilRepository;

	@PreAuthorize("hasAnyRole('GERENTE')")
	@GetMapping
	public List<UsuarioDTO> listarUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = UsuarioDTO.converter(usuarios);
		return usuariosDTO;
	}

	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscaUsuarioPorId(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOptional.get());
			return ResponseEntity.ok(usuarioDTO);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PreAuthorize("hasAnyRole('GERENTE')")
	@GetMapping("/perfil/{regra}")
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarioPorPerfil(@PathVariable String regra) {
		String regraUpperCase = "ROLE_" + regra.toUpperCase();
		Optional<Perfil> perfilOptional = perfilRepository.findById(regraUpperCase);
		if (perfilOptional.isPresent()) {
			Perfil perfil = perfilOptional.get();
			List<Usuario> usuarios = usuarioRepository.findByPerfil(perfil);
			List<UsuarioDTO> usuariosDTO = UsuarioDTO.converter(usuarios);
			return ResponseEntity.ok(usuariosDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> cadastroUsuario(@RequestBody @Valid UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioForm.converter();
		usuarioRepository.save(usuario);
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
//	@PreAuthorize("hasAnyRole('COMUM', 'TECNICO', 'GERENTE')")
//	@PutMapping("/{id}")
//	@Transactional
//	public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody @Valid AtualizaUsuarioForm atualizaUsuarioForm,
//			@PathVariable Long id) {
//		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
//		if (usuarioOptional.isPresent()) {
//			Usuario usuario = atualizaUsuarioForm.converter(usuarioOptional.get(), perfilRepository);
//			System.out.println(usuario.getAuthorities());
//			usuarioRepository.save(usuario);
//			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
//			return ResponseEntity.ok(usuarioDTO);
//		}
//		return ResponseEntity.notFound().build();
//	}

}
