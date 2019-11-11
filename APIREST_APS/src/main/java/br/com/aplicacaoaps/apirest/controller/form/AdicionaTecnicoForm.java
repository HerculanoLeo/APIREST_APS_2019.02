package br.com.aplicacaoaps.apirest.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.ChamadoRepository;

public class AdicionaTecnicoForm {

	@NotNull
	private Long idTecnico;
	
	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Long getIdTecnico() {
		return idTecnico;
	}
	
	public static Boolean adcionarNoChamado(Long idChamado, List<AdicionaTecnicoForm> listaTecnico, ChamadoRepository chamadoRepository) {
		try {
			Chamado chamado = chamadoRepository.getOne(idChamado);
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			listaTecnico.forEach(listTec -> {
				Usuario usuario = new Usuario();
				usuario.setId(listTec.getIdTecnico());
				listaUsuarios.add(usuario);
			});
			chamado.setTecnicos(listaUsuarios);
			chamadoRepository.save(chamado);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
