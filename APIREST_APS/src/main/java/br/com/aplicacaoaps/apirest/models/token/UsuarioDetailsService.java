package br.com.aplicacaoaps.apirest.models.token;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.UsuarioRepository;
/**
 * Classe que implemeta a UserDetailsService, especificação para recupera as infromações de username e password
 * 
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuario = Arrays.asList(usuarioRepository.findByEmail(username));
		
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + username + " não foi encontrado");
		}
		return usuario.get(0);
	}
}
