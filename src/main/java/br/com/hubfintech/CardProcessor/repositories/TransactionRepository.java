package br.com.hubfintech.CardProcessor.repositories;

import br.com.hubfintech.CardProcessor.entities.Transaction;

public interface TransactionRepository {
	
	/**
	 * Merge a transaction insert a new one or updating its fields; 
	 * @param transaction
	 */
	void merge(Transaction transaction);
	
}