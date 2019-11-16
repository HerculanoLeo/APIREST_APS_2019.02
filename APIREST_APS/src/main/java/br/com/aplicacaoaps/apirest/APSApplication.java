package br.com.aplicacaoaps.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Classe main que executa o servidor web tomcat embutido no spring, esta classe
 * é o ponto de partida.
 * 
 */

@SpringBootApplication
@EnableSpringDataWebSupport
public class APSApplication {

	public static void main(String[] args) {
		SpringApplication.run(APSApplication.class, args);
	}

}
