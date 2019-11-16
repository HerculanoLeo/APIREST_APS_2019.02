package br.com.aplicacaoaps.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.TagsVeiculo;
/**
 * Interface do Spring para manipulação do banco, referente ao TagsVeiculo
 */
public interface TagsVeiculoRepository extends JpaRepository<TagsVeiculo, Long> {

}
