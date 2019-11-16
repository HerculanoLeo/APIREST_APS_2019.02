package br.com.aplicacaoaps.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.Perfil;
/**
 * Interface do Spring para manipulação do banco, referente ao perfil
 */
public interface PerfilRepository extends JpaRepository<Perfil, String>{

}
