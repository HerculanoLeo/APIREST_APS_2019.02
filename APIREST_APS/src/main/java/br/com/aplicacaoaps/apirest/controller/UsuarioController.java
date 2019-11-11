package br.com.aplicacaoaps.apirest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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

import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;
import br.com.aplicacaoaps.apirest.controller.form.AtualizaUsuarioForm;
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

	@GetMapping
	public List<UsuarioDTO> listarUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = UsuarioDTO.converter(usuarios);
		return usuariosDTO;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscaUsuarioPorId(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOptional.get());
			return ResponseEntity.ok(usuarioDTO);
		}
		return ResponseEntity.notFound().build();
	}

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

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody @Valid AtualizaUsuarioForm atualizaUsuarioForm,
			@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			Usuario usuario = atualizaUsuarioForm.converter(usuarioOptional.get(), perfilRepository);
			System.out.println(usuario.getAuthorities());
			usuarioRepository.save(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		}
		return ResponseEntity.notFound().build();
	}
	
//	@GetMapping("/{id}/chamado")
//	public ResponseEntity<List<ChamadoListaDTO>> buscaOcorrenciaPoriDAutor(@PathVariable Long id) {
//		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
//		if (usuarioOptional.isPresent()) {
//			Usuario usuario = usuarioOptional.get();
//			List<Chamado> listaChamado = chamadoRepository.findByAutor(usuario);
//			List<ChamadoListaDTO> chamadosListaDTO = new ArrayList<ChamadoListaDTO>();
//			listaChamado.forEach(chamado -> {
//				chamadosListaDTO.add(new ChamadoListaDTO(chamado));
//			});
//			return ResponseEntity.ok(chamadosListaDTO);
//		}
//		return ResponseEntity.notFound().build();
//
//	}

}
