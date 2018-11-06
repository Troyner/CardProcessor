package br.com.hubfintech.CardProcessor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hubfintech.CardProcessor.businesses.CardBusiness;
import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.dtos.RequestTransactionDTO;
import br.com.hubfintech.CardProcessor.dtos.ResponseTransactionDTO;
import br.com.hubfintech.CardProcessor.exceptions.FindException;

/**
 * @author marcus.martins
 * @since 08/02/2018 
 */
@Controller
public class TransactionController {

	public static final String JSON = "application/json";
	
    @Autowired
    private CardBusiness business;

    @MessageMapping("/transaction")
    @SendTo("/transact")
    public ResponseTransactionDTO transact(RequestTransactionDTO requestTransactionDTO) {
    	return null;
    }
    
}
