package br.com.hubfintech.CardProcessor.repositories;

import java.util.Optional;

import br.com.hubfintech.CardProcessor.entities.Card;

public interface CardRepository {
	
	Optional<Card> findCardTransactions(String cardNumber, Integer transactionsAmount);
	Optional<Card> findByCardNumber(String cardNumber);
	void merge(Card card);
	
}