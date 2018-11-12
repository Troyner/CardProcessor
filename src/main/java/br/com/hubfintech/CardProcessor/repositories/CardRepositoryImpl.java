package br.com.hubfintech.CardProcessor.repositories;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.com.hubfintech.CardProcessor.entities.Card;
import br.com.hubfintech.CardProcessor.entities.Transaction;
import br.com.hubfintech.CardProcessor.entities.TransactionId;

@Repository
public class CardRepositoryImpl extends RepositoryImpl implements CardRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	final static String findCardTransactions =  " 	   SELECT card.id, " +
												"			  card.cardNumber, " +
												" 			  card.availableAmount, " +
												" 			  transaction.id.date, " +
												"	 		  transaction.id.amount " +
												" 		 FROM Transaction transaction " +
												"  RIGHT JOIN transaction.card as card " + 
												"		WHERE card.cardNumber = :cardNumber " +
												"	 ORDER BY transaction.id.date DESC ";
	
	/**
	 * Find card and its transactions by card number, delimiting by transaction amount. 
	 * @param cardNumber
	 * @param transactionsAmount
	 * @return Optional<Card>
	 */
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
			
			TransactionId transactionId = new TransactionId();
			transactionId.setDate(toLocalDateTime(registro[3]));
			transactionId.setAmount(toBigDecimal(registro[4]));
			
			transaction = new Transaction();
			transaction.setId(transactionId);
			transaction.setCard(card);
			
			card.getTransactions().add(transaction);
		}
		
		if (card != null) {
			cardOpitional = Optional.of(card);
		}
		
		return cardOpitional;
	}

	final static String findByCardNumber =  " SELECT card " +
											"   FROM Card card " +
											"  WHERE card.cardNumber = :cardNumber ";
	
	/**
	 * Find a card by card number.
	 * @param cardNumber
	 * @return Optional<Card>
	 */
	@Override
	public Optional<Card> findByCardNumber(String cardNumber) {
		Query query = entityManager.createQuery(findByCardNumber);
		query.setParameter("cardNumber", cardNumber);
		
		Optional<Card> cardOpitional = Optional.empty();
		
		Card card = null;
		try {
			card = (Card) query.getSingleResult();
		} catch (Exception e) {
			return cardOpitional;
		}
		
		if (card != null) {
			cardOpitional = Optional.of(card);
		}
		
		return cardOpitional;
	}

}