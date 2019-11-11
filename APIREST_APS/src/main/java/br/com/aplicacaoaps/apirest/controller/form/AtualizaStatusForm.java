package br.com.aplicacaoaps.apirest.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.aplicacaoaps.apirest.models.Chamado;
import br.com.aplicacaoaps.apirest.models.Status;
import br.com.aplicacaoaps.apirest.repository.ChamadoRepository;

public class AtualizaStatusForm {

	@NotNull
	private Status status;
	
	public void setStatus(String tipo) {
		this.status = Status.valueOf(tipo);
	}
	
	public Boolean alteraStatus(Long id, ChamadoRepository chamadoRepository ) {
		try {
			Chamado chamado = chamadoRepository.getOne(id);
			if(status==Status.FECHADO) {
				LocalDateTime dataFechamento = LocalDateTime.now();
				chamado.setDataFechamento(dataFechamento);
				chamado.setStatus(status);
			}else {
				chamado.setStatus(status);
			}
			chamadoRepository.save(chamado);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
