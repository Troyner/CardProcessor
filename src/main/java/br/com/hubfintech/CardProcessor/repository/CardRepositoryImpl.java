package br.com.hubfintech.CardProcessor.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.hubfintech.CardProcessor.entity.Card;
import br.com.hubfintech.CardProcessor.entity.Transaction;

@Repository
public class CardRepositoryImpl extends RepositoryImpl implements CardRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	final static String findCardTransactions =  " 	   SELECT card.id, " +
												"			  card.cardNumber, " +
												" 			  card.availableAmount, " +
												" 			  transaction.id, " +
												" 			  transaction.date, " +
												"	 		  transaction.amount " +
												" 		 FROM Transaction transaction " +
												"  INNER JOIN transaction.card as card " + 
												"		WHERE card.cardNumber = :cardNumber " +
												"	 ORDER BY transaction.date DESC ";
	
	@Override
	public Optional<Card> findCardTransactions(String cardNumber, Integer transactionsAmount) {
		Query query = entityManager.createQuery(findCardTransactions);
		query.setParameter("cardNumber", cardNumber);
		
		if (transactionsAmount != null && transactionsAmount > 0) {
			query.setMaxResults(transactionsAmount);
		}
		
		Optional<Card> cardOpitional = Optional.empty();
		
		Card card = null;
		Transaction transaction = null;
		
		List<Object[]> registros = query.getResultList();
		for (Object[] registro : registros) {
			if (card == null) {
				card = new Card();
				card.setId(toLong(registro[0]));
				card.setCardNumber(toString(registro[1]));
				card.setAvailableAmount(toBigDecimal(registro[2]));
				card.setTransactions(new ArrayList<>());
			}
			
			transaction = new Transaction();
			transaction.setId(toLong(registro[3]));
			transaction.setDate(toLocalDateTime(registro[4]));
			transaction.setAmount(toBigDecimal(registro[5]));
			
			card.getTransactions().add(transaction);
		}
		
		if (card != null) {
			cardOpitional = Optional.of(card);
		}
		
		return cardOpitional;
	}
	
	
	
}