package br.com.aplicacaoaps.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aplicacaoaps.apirest.models.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

														
	@Query(value = "select c from Comentario c where c.chamado.id = :id")
	List<Comentario> findByComentarioPorChamado_id(@Param("id") Long id);

}
