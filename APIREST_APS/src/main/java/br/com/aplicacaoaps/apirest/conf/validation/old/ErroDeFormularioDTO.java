package br.com.aplicacaoaps.apirest.conf.validation.old;

public class ErroDeFormularioDTO {

	String campo;
	String erro;

	public ErroDeFormularioDTO(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
