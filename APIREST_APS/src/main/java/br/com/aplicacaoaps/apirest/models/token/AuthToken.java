package br.com.aplicacaoaps.apirest.models.token;
/**
 * Classe usada como modelo para enviar os dados do token
 * 
 */
public class AuthToken {

	private String tipo = "Bearer ";
	private String token;

	public AuthToken() {

	}

	public AuthToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
