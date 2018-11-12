package br.com.hubfintech.CardProcessor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hubfintech.CardProcessor.businesses.CardBusiness;
import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.exceptions.FindException;

/**
 * Class responsible for being the implementation of the methods that will receive the requests of Card.
 * 
 * @author marcus.martins
 * @since 08/02/2018 
 */
@RestController
@RequestMapping("card")
public class CardController {

	public static final String JSON = "application/json";
	
    @Autowired
    private CardBusiness business;

    /**
     * Methods responsible for calling the business to fetch a Card and its last ten Transactions.
     * @return ResponseEntity<String>
     * @throws FindException 
     */
    @RequestMapping(value="/find-card-transactions", method = RequestMethod.GET, produces = { JSON })
    public ResponseEntity<CardDTO> buscarJson(String cardNumber) throws FindException {
		return ResponseEntity.ok(this.business.findCardTransactions(cardNumber));
    }
    
}
