package br.com.aplicacaoaps.apirest.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Ocorrencia;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.repository.OcorrenciaRepository;

public class AtualizaStatusForm {

	@NotNull
	private Status status;
	
	public void setStatus(String tipo) {
		this.status = Status.valueOf(tipo);
	}
	
	public Boolean alteraStatus(Long id, OcorrenciaRepository ocorrenciaRepository ) {
		try {
			Ocorrencia ocorrencia = ocorrenciaRepository.getOne(id);
			if(status==Status.FECHADO) {
				LocalDateTime dataFechamento = LocalDateTime.now();
				ocorrencia.setDataFechamento(dataFechamento);
				ocorrencia.setStatus(status);
			}else {
				ocorrencia.setStatus(status);
			}
			ocorrenciaRepository.save(ocorrencia);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
