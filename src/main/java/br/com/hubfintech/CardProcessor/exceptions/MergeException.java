package br.com.hubfintech.CardProcessor.exceptions;

/**
 * Exception genérica para tratar os erros
 * que aconteceram no processo de merge
 * as entidades.
 * @author marcus.martins
 */
public class MergeException extends Exception {
	private static final long serialVersionUID = 1L;

	public MergeException(String message) {
        super(message);
    }
	
	public MergeException(String message, Exception exception) {
        super(message, exception);
    }
}
