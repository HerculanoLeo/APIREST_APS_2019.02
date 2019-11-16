package br.com.aplicacaoaps.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.TipoChamado;
/**
 * Interface do Spring para manipulação do banco, referente ao TipoChamado
 */
public interface TipoChamadoRepository extends JpaRepository<TipoChamado, Long>{


}
