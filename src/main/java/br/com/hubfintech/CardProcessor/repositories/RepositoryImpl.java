package br.com.hubfintech.CardProcessor.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe abstrata com os atributos e metodos comuns para todos RepositoryVO´s
 * @author marcus.martins
 *
 */
public abstract class RepositoryImpl {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	/**
	 * Converte objeto (campo registro retornado pelo BD) para LocalDateTime.
	 */
	protected LocalDateTime toLocalDateTime(Object campoDoRegistro) {
		return (campoDoRegistro == null) ? null : LocalDateTime.parse(campoDoRegistro.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
//		2018-11-05T22:57:46.286
	}
	
	/**
	 * Converte objeto (campo registro retornado pelo BD) para Long.
	 */
	protected Long toLong(Object campoDoRegistro) {
		return (campoDoRegistro == null)? null : Long.valueOf(campoDoRegistro.toString());
	}
	
	/**
	 * Converte objeto (campo registro retornado pelo BD) para String.
	 */
	protected String toString(Object campoDoRegistro) {
		return (campoDoRegistro == null)? null : campoDoRegistro.toString();
	}
	
	/**
	 * Converte objeto (campo registro retornado pelo BD) para BigDecimal.
	 */
	protected BigDecimal toBigDecimal(Object campoDoRegistro) {
		return (campoDoRegistro == null) ? null : BigDecimal.valueOf(Double.valueOf(campoDoRegistro.toString().replace(",", ".")));
	}

}
