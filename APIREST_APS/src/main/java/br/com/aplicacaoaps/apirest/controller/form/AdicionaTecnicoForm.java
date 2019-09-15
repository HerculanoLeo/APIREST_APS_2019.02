package br.com.aplicacaoaps.apirest.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Usuario;
import br.com.aplicacaoaps.apirest.repository.OcorrenciaRepository;

public class AdicionaTecnicoForm {

	@NotNull
	private Long idTecnico;
	
	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Long getIdTecnico() {
		return idTecnico;
	}
	
	public static Boolean adcionarNaOcorrencia(Long idOcorrencia, List<AdicionaTecnicoForm> listaTecnico, OcorrenciaRepository ocorrenciaRepository) {
		try {
			Ocorrencia ocorrencia = ocorrenciaRepository.getOne(idOcorrencia);
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			listaTecnico.forEach(listTec -> {
				Usuario usuario = new Usuario();
				usuario.setId(listTec.getIdTecnico());
				listaUsuarios.add(usuario);
			});
			ocorrencia.setTecnicos(listaUsuarios);
			ocorrenciaRepository.save(ocorrencia);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
