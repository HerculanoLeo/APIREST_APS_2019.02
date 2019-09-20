package br.com.aplicacaoaps.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacaoaps.apirest.models.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, String>{

}
