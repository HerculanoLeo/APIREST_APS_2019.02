package br.com.aplicacaoaps.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.Tags;
/**
 * Interface do Spring para manipulação do banco, referente ao Tags
 */
public interface TagsRepository extends JpaRepository<Tags, Long>{

}
