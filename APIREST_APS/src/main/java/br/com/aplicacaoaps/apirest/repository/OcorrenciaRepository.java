package br.com.aplicacaoaps.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Usuario;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>{

	Optional<Ocorrencia> findById(Long id);

	List<Ocorrencia> findByAutor(Usuario usuario);
	
}
