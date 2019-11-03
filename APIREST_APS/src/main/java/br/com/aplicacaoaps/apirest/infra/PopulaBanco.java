package br.com.aplicacaoaps.apirest.infra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.aplicacaoaps.apirest.models.Comentarios;
import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Perfil;
import br.com.aplicacaoaps.apirest.models.TagsRegional;
import br.com.aplicacaoaps.apirest.models.TagsVeiculo;
import br.com.aplicacaoaps.apirest.models.TipoOcorrenciaRegional;
import br.com.aplicacaoaps.apirest.models.TipoOcorrenciaVeiculo;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;
import br.com.aplicacaoaps.apirest.repository.OcorrenciaRepository;
import br.com.aplicacaoaps.apirest.repository.PerfilRepository;
import br.com.aplicacaoaps.apirest.repository.TagsRegionalRepository;
import br.com.aplicacaoaps.apirest.repository.TagsVeiculoRepository;
import br.com.aplicacaoaps.apirest.repository.TipoOcorrenciaRepository;
import br.com.aplicacaoaps.apirest.repository.UsuarioRepository;

public class PopulaBanco {

	public static void populaUsuario(UsuarioRepository usuarioRepository, OcorrenciaRepository ocorrenciaRepository,
			ComentarioRepository comentarioRepository, TagsVeiculoRepository tagsVeiculoRepository,
			TagsRegionalRepository tagsRegionalRepository, TipoOcorrenciaRepository tipoOcorrenciaRepository,
			PerfilRepository perfilRepository) {

		Usuario usuarioComum = new Usuario();
		Usuario usuarioTecnico1 = new Usuario();
		Usuario usuarioTecnico2 = new Usuario();
		Usuario usuarioGerente = new Usuario();

		Perfil perfilComum = new Perfil();
		Perfil perfilTecnico = new Perfil();
		Perfil perfilGerente = new Perfil();

		perfilComum.setNome("ROLE_COMUM");
		perfilRepository.save(perfilComum);
		perfilTecnico.setNome("ROLE_TECNICO");
		perfilRepository.save(perfilTecnico);
		perfilGerente.setNome("ROLE_GERENTE");
		perfilRepository.save(perfilGerente);

		usuarioComum.setNome("UserComum");
		usuarioComum.setEmail("comum@email.com");
		usuarioComum.setSenha("123456");
		usuarioComum.setPerfil(Arrays.asList(perfilComum));

		usuarioTecnico1.setNome("UserTecnico1");
		usuarioTecnico1.setEmail("tecnico1@email.com");
		usuarioTecnico1.setSenha("123456");
		usuarioTecnico1.setPerfil(Arrays.asList(perfilTecnico));

		usuarioTecnico2.setNome("UserTecnico2");
		usuarioTecnico2.setEmail("tecnico2@email.com");
		usuarioTecnico2.setSenha("123456");
		usuarioTecnico2.setPerfil(Arrays.asList(perfilTecnico));

		usuarioGerente.setNome("UserGerente");
		usuarioGerente.setEmail("gerente@email.com");
		usuarioGerente.setSenha("123456");
		usuarioGerente.setPerfil(Arrays.asList(perfilGerente));

		Ocorrencia ocorrencia1 = new Ocorrencia();

		ocorrencia1.setAutor(usuarioComum);
		List<Usuario> tecnicos1 = new ArrayList<Usuario>();
		tecnicos1.add(usuarioTecnico1);
		tecnicos1.add(usuarioTecnico2);
		ocorrencia1.setTecnicos(tecnicos1);
		ocorrencia1.setTitulo_Ocorrencia("Carro em situação precária "+ "-1");
		ocorrencia1.setDescricao_Ocorrencia("Carro emitindo fumaça preta e muito barulho do escapamento");

		TipoOcorrenciaVeiculo tipoOcorrencia1 = new TipoOcorrenciaVeiculo();
		tipoOcorrencia1.setPlaca("XXX7777");

		TagsVeiculo tagVeiculo1 = new TagsVeiculo();
		TagsVeiculo tagVeiculo2 = new TagsVeiculo();
		tagVeiculo1.setNome("Barulho");
		tagVeiculo2.setNome("Fumaca");
		List<TagsVeiculo> listaTagsVeiculo = new ArrayList<TagsVeiculo>();
		listaTagsVeiculo.add(tagVeiculo1);
		listaTagsVeiculo.add(tagVeiculo2);
		tagsVeiculoRepository.saveAll(listaTagsVeiculo);
		tipoOcorrencia1.setTags(listaTagsVeiculo);
		tipoOcorrenciaRepository.save(tipoOcorrencia1);

		ocorrencia1.setTipoOcorrencia(tipoOcorrencia1);

		Comentarios comentario1 = new Comentarios();
		comentario1.setAutor(usuarioComum);
		comentario1.setResposta("Chamado aberto UserComum Veiculo");
		comentario1.setOcorrencia(ocorrencia1);

		Comentarios comentario2 = new Comentarios();
		comentario2.setAutor(usuarioTecnico1);
		comentario2.setResposta("Estou atendendo Tecnico1 Veiculo");
		comentario2.setOcorrencia(ocorrencia1);

		List<Comentarios> comentariosVeiculo = new ArrayList<Comentarios>();
		comentariosVeiculo.add(comentario1);
		comentariosVeiculo.add(comentario2);

		usuarioRepository.save(usuarioComum);
		usuarioRepository.save(usuarioTecnico1);
		usuarioRepository.save(usuarioTecnico2);
		usuarioRepository.save(usuarioGerente);

		comentarioRepository.saveAll(comentariosVeiculo);

		ocorrenciaRepository.save(ocorrencia1);

		Ocorrencia ocorrencia2 = new Ocorrencia();

		ocorrencia2.setAutor(usuarioComum);
		List<Usuario> tecnicos2 = new ArrayList<Usuario>();
		tecnicos2.add(usuarioTecnico1);
		tecnicos2.add(usuarioGerente);
		ocorrencia2.setTecnicos(tecnicos2);
		ocorrencia2.setTitulo_Ocorrencia("Região com Alagamento "+ "-1");
		ocorrencia2.setDescricao_Ocorrencia("Bueiros vivem entupidos, causando alagamentos em nosso bairro");

		TipoOcorrenciaRegional tipoOcorrencia2 = new TipoOcorrenciaRegional();
		tipoOcorrencia2.setCEP(13201000);

		TagsRegional tagsRegional1 = new TagsRegional();
		TagsRegional tagsRegional2 = new TagsRegional();
		tagsRegional1.setNome("Alagamento");
		tagsRegional2.setNome("Lixo");
		List<TagsRegional> listaTagsRegional = new ArrayList<TagsRegional>();
		listaTagsRegional.add(tagsRegional1);
		listaTagsRegional.add(tagsRegional2);
		tagsRegionalRepository.saveAll(listaTagsRegional);

		tipoOcorrencia2.setTags(listaTagsRegional);

		tipoOcorrenciaRepository.save(tipoOcorrencia2);

		ocorrencia2.setTipoOcorrencia(tipoOcorrencia2);

		Comentarios comentarioRegional1 = new Comentarios();
		comentarioRegional1.setAutor(usuarioComum);
		comentarioRegional1.setResposta("Chamado aberto UserComum Regional");
		comentarioRegional1.setOcorrencia(ocorrencia2);

		Comentarios comentarioRegional2 = new Comentarios();
		comentarioRegional2.setAutor(usuarioGerente);
		comentarioRegional2.setResposta("Estou atendendo Gerente Regional");
		comentarioRegional2.setOcorrencia(ocorrencia2);

		List<Comentarios> comentariosRegional = new ArrayList<Comentarios>();
		comentariosRegional.add(comentarioRegional1);
		comentariosRegional.add(comentarioRegional2);

		comentarioRepository.saveAll(comentariosRegional);

		ocorrenciaRepository.save(ocorrencia2);

		for (int i = 0; i < 20; i++) {

			Ocorrencia ocorrencia3 = new Ocorrencia();
			ocorrencia3.setAutor(usuarioComum);
			ocorrencia3.setTecnicos(tecnicos1);
			ocorrencia3.setTitulo_Ocorrencia("Carro em situação precária " + i);
			ocorrencia3.setDescricao_Ocorrencia("Carro emitindo fumaça preta e muito barulho do escapamento");

			TipoOcorrenciaVeiculo tipoOcorrencia3 = new TipoOcorrenciaVeiculo();
			tipoOcorrencia3.setPlaca("XXX7777");
			tipoOcorrencia3.setTags(listaTagsVeiculo);
			tipoOcorrenciaRepository.save(tipoOcorrencia3);

			ocorrencia3.setTipoOcorrencia(tipoOcorrencia3);
			
			Comentarios comentario3 = new Comentarios();
			comentario3.setAutor(usuarioComum);
			comentario3.setResposta("Chamado aberto UserComum Veiculo");
			comentario3.setOcorrencia(ocorrencia3);

			Comentarios comentario4 = new Comentarios();
			comentario4.setAutor(usuarioTecnico1);
			comentario4.setResposta("Estou atendendo Tecnico1 Veiculo");
			comentario4.setOcorrencia(ocorrencia3);
			
			List<Comentarios> comentariosVeiculo2 = new ArrayList<Comentarios>();
			comentariosVeiculo2.add(comentario3);
			comentariosVeiculo2.add(comentario4);
			
			comentarioRepository.saveAll(comentariosVeiculo2);
			
			ocorrenciaRepository.save(ocorrencia3);
			
			
			Ocorrencia ocorrencia4 = new Ocorrencia();

			ocorrencia4.setAutor(usuarioComum);
			ocorrencia4.setTecnicos(tecnicos2);
			ocorrencia4.setTitulo_Ocorrencia("Região com Alagamento " + i);
			ocorrencia4.setDescricao_Ocorrencia("Bueiros vivem entupidos, causando alagamentos em nosso bairro");

			TipoOcorrenciaRegional tipoOcorrencia4 = new TipoOcorrenciaRegional();
			tipoOcorrencia4.setCEP(13201000);

			tipoOcorrencia4.setTags(listaTagsRegional);

			tipoOcorrenciaRepository.save(tipoOcorrencia4);

			ocorrencia4.setTipoOcorrencia(tipoOcorrencia4);

			Comentarios comentarioRegional4 = new Comentarios();
			comentarioRegional4.setAutor(usuarioComum);
			comentarioRegional4.setResposta("Chamado aberto UserComum Regional");
			comentarioRegional4.setOcorrencia(ocorrencia4);

			Comentarios comentarioRegional6 = new Comentarios();
			comentarioRegional6.setAutor(usuarioGerente);
			comentarioRegional6.setResposta("Estou atendendo Gerente Regional");
			comentarioRegional6.setOcorrencia(ocorrencia4);

			List<Comentarios> comentariosRegional2 = new ArrayList<Comentarios>();
			comentariosRegional2.add(comentarioRegional4);
			comentariosRegional2.add(comentarioRegional6);

			comentarioRepository.saveAll(comentariosRegional2);

			ocorrenciaRepository.save(ocorrencia4);
			
			
			
		}

	}

}
