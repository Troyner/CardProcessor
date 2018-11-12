package br.com.hubfintech.CardProcessor.services;

import br.com.hubfintech.CardProcessor.entities.Transaction;
import br.com.hubfintech.CardProcessor.exceptions.MergeException;

public interface TransactionService {

	/**
	 * Merge a transaction insert or updating its fields; 
	 * @param transaction
	 * @throws MergeException
	 */
	void merge(Transaction transaction) throws MergeException;
	
}
