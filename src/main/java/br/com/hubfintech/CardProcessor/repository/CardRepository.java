package br.com.hubfintech.CardProcessor.repository;

import java.util.Optional;

import br.com.hubfintech.CardProcessor.entity.Card;

public interface CardRepository {
	
	Optional<Card> findCardTransactions(String cardNumber, Integer transactionsAmount);
	
}