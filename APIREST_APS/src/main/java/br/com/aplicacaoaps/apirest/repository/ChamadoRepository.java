package br.com.aplicacaoaps.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.models.Usuario;

public interface ChamadoRepository extends JpaRepository<Chamado, Long>{

	Optional<Chamado> findById(Long id);

	List<Chamado> findByAutor(Usuario usuario);
	Page<Chamado> findByStatus(Status status, Pageable pageable);

	Page<Chamado> findByTecnicosAndStatus(List<Usuario> tecnicos, Status status, Pageable pageable);
	Page<Chamado> findByAutorAndStatus(Usuario usuario, Status status, Pageable pageable);
	

}
