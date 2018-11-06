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
import br.com.hubfintech.CardProcessor.exceptions.MergeException;
import br.com.hubfintech.CardProcessor.exceptions.PersistException;
import br.com.hubfintech.CardProcessor.repositories.CardRepositoryImpl;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepositoryImpl repository;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public CardDTO findCardTransactions(String cardNumber, Integer transactionsAmount) throws FindException {
		return buildCardDTO(repository.findCardTransactions(cardNumber, transactionsAmount).orElseThrow(() -> new FindException("Card not found")));
	}
	
	@Override
	public Card findByCardNumber(String cardNumber) throws FindException {
		return repository.findByCardNumber(cardNumber).orElseThrow(() -> new FindException("Card not found"));
	}
	
//	@Override
//	public void merge(Card card) throws MergeException {
//		try {
//			this.repository.merge(card);
//		} catch (Exception e) {
//			throw new MergeException("Error at transaction insert", e);
//		}
//	}
	
	private CardDTO buildCardDTO(Card card) {
		return CardDTO.builder()
				.cardNumber(card.getCardNumber())
				.availableAmount(card.getAvailableAmount())
				.transactions(buildListTransactionsDTO(card.getTransactions()))
				.build();
	}
	
	private List<TransactionDTO> buildListTransactionsDTO(List<Transaction> transactions) {
		List<TransactionDTO> tranctionsDTOs = new ArrayList<>();
		transactions.stream().filter(t -> t.getId().getDate() != null).forEach(t -> tranctionsDTOs.add(buildTransactionsDTO(t)));
		return tranctionsDTOs;
	}
	
	private TransactionDTO buildTransactionsDTO(Transaction transaction) {
		return TransactionDTO.builder()
				.date(formatter.format(transaction.getId().getDate()))
				.amount(transaction.getId().getAmount().toString())
				.build();
	}

}
