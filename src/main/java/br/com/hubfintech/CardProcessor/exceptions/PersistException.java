package br.com.hubfintech.CardProcessor.exceptions;

/**
 * Exception genérica para tratar os erros
 * que aconteceram no processo de inserção
 * as entidades.
 * @author marcus.martins
 */
public class PersistException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersistException(String message) {
        super(message);
    }
	
	public PersistException(String message, Exception exception) {
        super(message, exception);
    }
}
