package br.com.hubfintech.CardProcessor.services;

import br.com.hubfintech.CardProcessor.entities.Transaction;
import br.com.hubfintech.CardProcessor.exceptions.MergeException;

public interface TransactionService {

	void merge(Transaction transaction) throws MergeException;
	
}
