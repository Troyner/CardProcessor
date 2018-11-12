package br.com.hubfintech.CardProcessor.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.dtos.TransactionDTO;
import br.com.hubfintech.CardProcessor.entities.Card;
import br.com.hubfintech.CardProcessor.entities.Transaction;
import br.com.hubfintech.CardProcessor.exceptions.FindException;
import br.com.hubfintech.CardProcessor.repositories.CardRepositoryImpl;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepositoryImpl repository;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * Find card and its transactions by card number, delimiting by transaction amount. 
	 * @param cardNumber
	 * @param transactionsAmount
	 * @return CardDTO
	 * @throws FindException
	 */
	@Override
	public CardDTO findCardTransactions(String cardNumber, Integer transactionsAmount) throws FindException {
		return buildCardDTO(repository.findCardTransactions(cardNumber, transactionsAmount).orElseThrow(() -> new FindException("Card not found")));
	}
	
	/**
	 * Find a card by card number.
	 * @param cardNumber
	 * @return Card
	 * @throws FindException
	 */
	@Override
	public Card findByCardNumber(String cardNumber) throws FindException {
		return repository.findByCardNumber(cardNumber).orElseThrow(() -> new FindException("Card not found"));
	}
	
	/**
	 * Build a CardDTO using pattern builder.
	 * @param card
	 * @return CardDTO
	 */
	private CardDTO buildCardDTO(Card card) {
		return CardDTO.builder()
				.cardNumber(card.getCardNumber())
				.availableAmount(card.getAvailableAmount())
				.transactions(buildListTransactionsDTO(card.getTransactions()))
				.build();
	}
	
	/**
	 * Build a List<TransactionDTO> checkin with the transaction have date.
	 * @param List<TransactionDTO>
	 * @return
	 */
	private List<TransactionDTO> buildListTransactionsDTO(List<Transaction> transactions) {
		List<TransactionDTO> tranctionsDTOs = new ArrayList<>();
		transactions.stream().filter(t -> t.getId().getDate() != null).forEach(t -> tranctionsDTOs.add(buildTransactionsDTO(t)));
		return tranctionsDTOs;
	}
	
	/**
	 * Build a TransactionDTO using pattern builder.
	 * @param transaction
	 * @return TransactionDTO
	 */
	private TransactionDTO buildTransactionsDTO(Transaction transaction) {
		return TransactionDTO.builder()
				.date(formatter.format(transaction.getId().getDate()))
				.amount(transaction.getId().getAmount())
				.build();
	}

}
