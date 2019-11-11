package br.com.aplicacaoaps.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.TipoChamado;

public interface TipoChamadoRepository extends JpaRepository<TipoChamado, Long>{

}
