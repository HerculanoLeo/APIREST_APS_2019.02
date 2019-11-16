package br.com.aplicacaoaps.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.TagsRegional;
/**
 * Interface do Spring para manipulação do banco, referente ao TagRegional
 */
public interface TagsRegionalRepository extends JpaRepository<TagsRegional, Long>{

}
