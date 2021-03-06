package br.com.aplicacaoaps.apirest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aplicacaoaps.apirest.conf.security.TokenProvider;
import br.com.aplicacaoaps.apirest.controller.dto.UsuarioDTO;
import br.com.aplicacaoaps.apirest.controller.form.LoginForm;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.models.token.AuthToken;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm loginUser){
		try {
			final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getSenha()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(authentication);
			UsuarioDTO usuarioDTO = new UsuarioDTO((Usuario) authentication.getPrincipal());
			return ResponseEntity.ok(new AuthToken(token, usuarioDTO));
		} catch (AuthenticationException e) {
			throw e;
		}
	}

}