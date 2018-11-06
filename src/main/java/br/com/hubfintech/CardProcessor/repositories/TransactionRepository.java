package br.com.hubfintech.CardProcessor.repositories;

import br.com.hubfintech.CardProcessor.entities.Transaction;

public interface TransactionRepository {
	
	void merge(Transaction transaction);
	
}