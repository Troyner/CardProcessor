package br.com.hubfintech.CardProcessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hubfintech.CardProcessor.business.CardBusiness;
import br.com.hubfintech.CardProcessor.dto.CardDTO;
import br.com.hubfintech.CardProcessor.exception.FindException;

/**
 * Classe responsável por ser a implementação dos
 * métodos que receberão os requests de Card.
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
     * Métodos responsável por chamar o business para buscar um Card e sua últimas dez Transactions.
     * @return ResponseEntity<String>
     * @throws FindException 
     */
    @RequestMapping(value="/find-card-transactions", method = RequestMethod.GET, produces = { JSON })
    public ResponseEntity<CardDTO> buscarJson(String cardNumber) throws FindException {
		return ResponseEntity.ok(this.business.findCardTransactions(cardNumber));
    }
    
}
