package br.com.hubfintech.CardProcessor.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.com.hubfintech.CardProcessor.entities.Transaction;

@Repository
public class TransactionRepositoryImpl extends RepositoryImpl implements TransactionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Merge a transaction insert or updating its fields; 
	 * @param transaction
	 */
	@Override
	public void merge(Transaction transaction) {
		try {
			Session session = entityManager.unwrap(Session.class);

			session.doWork(conn -> {
			session.merge(transaction);
			session.flush();
			session.clear();
			});
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}