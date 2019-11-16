package br.com.aplicacaoaps.apirest.infra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.aplicacaoaps.apirest.models.Comentario;
import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Perfil;
import br.com.aplicacaoaps.apirest.models.TagsRegional;
import br.com.aplicacaoaps.apirest.models.TagsVeiculo;
import br.com.aplicacaoaps.apirest.models.TipoChamadoRegional;
import br.com.aplicacaoaps.apirest.models.TipoChamadoVeiculo;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.ComentarioRepository;
import br.com.aplicacaoaps.apirest.repository.ChamadoRepository;
import br.com.aplicacaoaps.apirest.repository.PerfilRepository;
import br.com.aplicacaoaps.apirest.repository.TagsRegionalRepository;
import br.com.aplicacaoaps.apirest.repository.TagsVeiculoRepository;
import br.com.aplicacaoaps.apirest.repository.TipoChamadoRepository;
import br.com.aplicacaoaps.apirest.repository.UsuarioRepository;
/**
 * Classe usada para popular o banco
 * 
 */
public class PopulaBanco {

	public static void popular(UsuarioRepository usuarioRepository, ChamadoRepository chamadoRepository,
			ComentarioRepository comentarioRepository, TagsVeiculoRepository tagsVeiculoRepository,
			TagsRegionalRepository tagsRegionalRepository, TipoChamadoRepository tipoChamadoRepository,
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

		Chamado chamado1 = new Chamado();

		chamado1.setAutor(usuarioComum);
		List<Usuario> tecnicos1 = new ArrayList<Usuario>();
		tecnicos1.add(usuarioTecnico1);
		chamado1.setTecnicos(tecnicos1);
		chamado1.setTitulo_Chamado("Carro em situação precária "+ "-1");
		chamado1.setDescricao_Chamado("Carro emitindo fumaça preta e muito barulho do escapamento");

		TipoChamadoVeiculo tipoChamado1 = new TipoChamadoVeiculo();
		tipoChamado1.setPlaca("XXX7777");

		TagsVeiculo tagVeiculo1 = new TagsVeiculo();
		TagsVeiculo tagVeiculo2 = new TagsVeiculo();
		tagVeiculo1.setNome("Barulho");
		tagVeiculo2.setNome("Fumaca");
		List<TagsVeiculo> listaTagsVeiculo = new ArrayList<TagsVeiculo>();
		listaTagsVeiculo.add(tagVeiculo1);
		listaTagsVeiculo.add(tagVeiculo2);
		tagsVeiculoRepository.saveAll(listaTagsVeiculo);
		tipoChamado1.setTags(listaTagsVeiculo);

		chamado1.setTipoChamado(tipoChamado1);

		Comentario comentario1 = new Comentario();
		comentario1.setAutor(usuarioComum);
		comentario1.setResposta("Chamado aberto UserComum Veiculo");
		comentario1.setChamado(chamado1);

		Comentario comentario2 = new Comentario();
		comentario2.setAutor(usuarioTecnico1);
		comentario2.setResposta("Estou atendendo Tecnico1 Veiculo");
		comentario2.setChamado(chamado1);

		List<Comentario> comentariosVeiculo = new ArrayList<Comentario>();
		comentariosVeiculo.add(comentario1);
		comentariosVeiculo.add(comentario2);

		usuarioRepository.save(usuarioComum);
		usuarioRepository.save(usuarioTecnico1);
		usuarioRepository.save(usuarioTecnico2);
		usuarioRepository.save(usuarioGerente);


		tipoChamadoRepository.save(tipoChamado1);

		comentarioRepository.saveAll(comentariosVeiculo);
		
		chamadoRepository.save(chamado1);

		Chamado chamado2 = new Chamado();

		chamado2.setAutor(usuarioComum);
		List<Usuario> tecnicos2 = new ArrayList<Usuario>();
		tecnicos2.add(usuarioTecnico2);
		tecnicos2.add(usuarioGerente);
		chamado2.setTecnicos(tecnicos2);
		chamado2.setTitulo_Chamado("Região com Alagamento "+ "-1");
		chamado2.setDescricao_Chamado("Bueiros vivem entupidos, causando alagamentos em nosso bairro");

		TipoChamadoRegional tipoChamado2 = new TipoChamadoRegional();
		tipoChamado2.setCEP(13201000);

		TagsRegional tagsRegional1 = new TagsRegional();
		TagsRegional tagsRegional2 = new TagsRegional();
		tagsRegional1.setNome("Alagamento");
		tagsRegional2.setNome("Lixo");
		List<TagsRegional> listaTagsRegional = new ArrayList<TagsRegional>();
		listaTagsRegional.add(tagsRegional1);
		listaTagsRegional.add(tagsRegional2);
		tagsRegionalRepository.saveAll(listaTagsRegional);

		tipoChamado2.setTags(listaTagsRegional);

		tipoChamadoRepository.save(tipoChamado2);

		chamado2.setTipoChamado(tipoChamado2);

		Comentario comentarioRegional1 = new Comentario();
		comentarioRegional1.setAutor(usuarioComum);
		comentarioRegional1.setResposta("Chamado aberto UserComum Regional");
		comentarioRegional1.setChamado(chamado2);

		Comentario comentarioRegional2 = new Comentario();
		comentarioRegional2.setAutor(usuarioGerente);
		comentarioRegional2.setResposta("Estou atendendo Gerente Regional");
		comentarioRegional2.setChamado(chamado2);

		List<Comentario> comentariosRegional = new ArrayList<Comentario>();
		comentariosRegional.add(comentarioRegional1);
		comentariosRegional.add(comentarioRegional2);

		comentarioRepository.saveAll(comentariosRegional);

		chamadoRepository.save(chamado2);

		for (int i = 0; i < 20; i++) {

			Chamado chamado3 = new Chamado();
			chamado3.setAutor(usuarioComum);
			chamado3.setTecnicos(tecnicos1);
			chamado3.setTitulo_Chamado("Carro em situação precária " + i);
			chamado3.setDescricao_Chamado("Carro emitindo fumaça preta e muito barulho do escapamento");

			TipoChamadoVeiculo tipoChamado3 = new TipoChamadoVeiculo();
			tipoChamado3.setPlaca("XXX7777");
			tipoChamado3.setTags(listaTagsVeiculo);
			tipoChamadoRepository.save(tipoChamado3);

			chamado3.setTipoChamado(tipoChamado3);
			
			Comentario comentario3 = new Comentario();
			comentario3.setAutor(usuarioComum);
			comentario3.setResposta("Chamado aberto UserComum Veiculo");
			comentario3.setChamado(chamado3);

			Comentario comentario4 = new Comentario();
			comentario4.setAutor(usuarioTecnico1);
			comentario4.setResposta("Estou atendendo Tecnico1 Veiculo");
			comentario4.setChamado(chamado3);
			
			List<Comentario> comentariosVeiculo2 = new ArrayList<Comentario>();
			comentariosVeiculo2.add(comentario3);
			comentariosVeiculo2.add(comentario4);
			
			comentarioRepository.saveAll(comentariosVeiculo2);
			
			chamadoRepository.save(chamado3);
			
			
			Chamado chamado4 = new Chamado();

			chamado4.setAutor(usuarioComum);
			chamado4.setTecnicos(tecnicos2);
			chamado4.setTitulo_Chamado("Região com Alagamento " + i);
			chamado4.setDescricao_Chamado("Bueiros vivem entupidos, causando alagamentos em nosso bairro");

			TipoChamadoRegional tipoChamado4 = new TipoChamadoRegional();
			tipoChamado4.setCEP(13201000);

			tipoChamado4.setTags(listaTagsRegional);

			tipoChamadoRepository.save(tipoChamado4);

			chamado4.setTipoChamado(tipoChamado4);

			Comentario comentarioRegional4 = new Comentario();
			comentarioRegional4.setAutor(usuarioComum);
			comentarioRegional4.setResposta("Chamado aberto UserComum Regional");
			comentarioRegional4.setChamado(chamado4);

			Comentario comentarioRegional6 = new Comentario();
			comentarioRegional6.setAutor(usuarioGerente);
			comentarioRegional6.setResposta("Estou atendendo Gerente Regional");
			comentarioRegional6.setChamado(chamado4);

			List<Comentario> comentariosRegional2 = new ArrayList<Comentario>();
			comentariosRegional2.add(comentarioRegional4);
			comentariosRegional2.add(comentarioRegional6);

			comentarioRepository.saveAll(comentariosRegional2);

			chamadoRepository.save(chamado4);
			
			
			
		}

	}

}
