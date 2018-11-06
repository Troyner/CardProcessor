package br.com.hubfintech.CardProcessor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hubfintech.CardProcessor.entities.Transaction;
import br.com.hubfintech.CardProcessor.exceptions.MergeException;
import br.com.hubfintech.CardProcessor.exceptions.PersistException;
import br.com.hubfintech.CardProcessor.repositories.CardRepositoryImpl;
import br.com.hubfintech.CardProcessor.repositories.TransactionRepositoryImpl;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepositoryImpl repository;
	
	@Autowired
	private CardRepositoryImpl cardRepositoryImpl;

	@Transactional
	@Override
	public void merge(Transaction transaction) throws MergeException {
		try {
			this.repository.merge(transaction);
		} catch (Exception e) {
			throw new MergeException("Error at transaction insert", e);
		}
	}

}
