package br.com.aplicacaoaps.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.Perfil;
import br.com.aplicacaoaps.apirest.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByPerfil(Perfil perfil);
	
}
