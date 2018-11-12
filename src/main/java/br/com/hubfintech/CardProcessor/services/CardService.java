package br.com.hubfintech.CardProcessor.services;

import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.entities.Card;
import br.com.hubfintech.CardProcessor.exceptions.FindException;

public interface CardService {

	/**
	 * Find card and its transactions by card number, delimiting by transaction amount. 
	 * @param cardNumber
	 * @param transactionsAmount
	 * @return CardDTO
	 * @throws FindException
	 */
	CardDTO findCardTransactions(String cardNumber, Integer transactionsAmount) throws FindException;
	
	/**
	 * Find a card by card number.
	 * @param cardNumber
	 * @return Card
	 * @throws FindException
	 */
	Card findByCardNumber(String cardNumber) throws FindException;
	
}
