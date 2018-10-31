package br.com.hubfintech.CardProcessor.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hubfintech.CardProcessor.dto.CardDTO;
import br.com.hubfintech.CardProcessor.dto.TransactionDTO;
import br.com.hubfintech.CardProcessor.entity.Card;
import br.com.hubfintech.CardProcessor.entity.Transaction;
import br.com.hubfintech.CardProcessor.exception.FindException;
import br.com.hubfintech.CardProcessor.repository.CardRepositoryImpl;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepositoryImpl repository;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public CardDTO findCardTransactions(String cardNumber, Integer transactionsAmount) throws FindException {
		return buildCardDTO(repository.findCardTransactions(cardNumber, transactionsAmount).orElseThrow(() -> new FindException("Card not found")));
	}
	
	private CardDTO buildCardDTO(Card card) {
		return CardDTO.builder()
				.cardNumber(card.getCardNumber())
				.availableAmount(card.getAvailableAmount())
				.transactions(buildListTransactionsDTO(card.getTransactions()))
				.build();
	}
	
	private List<TransactionDTO> buildListTransactionsDTO(List<Transaction> transactions) {
		List<TransactionDTO> tranctionsDTOs = new ArrayList<>();
		transactions.forEach(t -> tranctionsDTOs.add(buildTransactionsDTO(t)));
		return tranctionsDTOs;
	}
	
	private TransactionDTO buildTransactionsDTO(Transaction transaction) {
		return TransactionDTO.builder()
				.date(formatter.format(transaction.getDate()))
				.amount(transaction.getAmount().toString())
				.build();
	}
	
}
