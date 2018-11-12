package br.com.hubfintech.CardProcessor.repositories;

import java.util.Optional;

import br.com.hubfintech.CardProcessor.entities.Card;

public interface CardRepository {
	
	/**
	 * Find card and its transactions by card number, delimiting by transaction amount. 
	 * @param cardNumber
	 * @param transactionsAmount
	 * @return Optional<Card>
	 */
	Optional<Card> findCardTransactions(String cardNumber, Integer transactionsAmount);
	
	/**
	 * Find a card by card number.
	 * @param cardNumber
	 * @return Optional<Card>
	 */
	Optional<Card> findByCardNumber(String cardNumber);
	
}