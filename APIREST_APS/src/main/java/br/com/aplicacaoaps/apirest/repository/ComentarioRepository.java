package br.com.aplicacaoaps.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aplicacaoaps.apirest.models.Comentarios;

public interface ComentarioRepository extends JpaRepository<Comentarios, Long> {

														
	@Query(value = "select c from Comentarios c where c.ocorrencia.id = :id")
	List<Comentarios> findByComentarioPorOcorencia_id(@Param("id") Long id);

}
