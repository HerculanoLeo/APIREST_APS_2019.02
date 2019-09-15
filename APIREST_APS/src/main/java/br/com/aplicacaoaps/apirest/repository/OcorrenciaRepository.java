package br.com.aplicacaoaps.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>{

	Optional<Ocorrencia> findById(Long id);
	
}
