package br.com.hubfintech.CardProcessor.service;

import br.com.hubfintech.CardProcessor.dto.CardDTO;
import br.com.hubfintech.CardProcessor.exception.FindException;

public interface CardService {

	CardDTO findCardTransactions(String cardNumber, Integer transactionsAmount) throws FindException ;
	
}
