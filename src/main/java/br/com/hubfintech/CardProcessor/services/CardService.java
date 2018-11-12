package br.com.hubfintech.CardProcessor.services;

import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.entities.Card;
import br.com.hubfintech.CardProcessor.exceptions.FindException;

public interface CardService {

	CardDTO findCardTransactions(String cardNumber, Integer transactionsAmount) throws FindException;
	Card findByCardNumber(String cardNumber) throws FindException;
	
}
