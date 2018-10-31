package br.com.hubfintech.CardProcessor.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Classe responsável por interceptar as Exceptions tratadas e
 * lançadas a partir das classes Controller's.
 * @author marcus.martins
 *
 */
@ControllerAdvice
public class ApiExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(FindException.class)
    public ResponseEntity<Object> handleError(FindException ex) {
    	LOGGER.error(ex.getMessage(), ex);
    	return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
}
