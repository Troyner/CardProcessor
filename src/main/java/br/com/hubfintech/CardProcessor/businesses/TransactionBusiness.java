package br.com.hubfintech.CardProcessor.businesses;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.hubfintech.CardProcessor.dtos.RequestTransactionDTO;
import br.com.hubfintech.CardProcessor.dtos.ResponseTransactionDTO;
import br.com.hubfintech.CardProcessor.entities.Card;
import br.com.hubfintech.CardProcessor.entities.Transaction;
import br.com.hubfintech.CardProcessor.entities.TransactionId;
import br.com.hubfintech.CardProcessor.enums.Action;
import br.com.hubfintech.CardProcessor.exceptions.FindException;
import br.com.hubfintech.CardProcessor.exceptions.PersistException;
import br.com.hubfintech.CardProcessor.services.CardService;
import br.com.hubfintech.CardProcessor.services.TransactionService;

@Component
public class TransactionBusiness {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CardService cardService;

	public ResponseTransactionDTO transact(RequestTransactionDTO requestTransactionDTO) throws PersistException {
		
		ResponseTransactionDTO responseTransactionDTO = new ResponseTransactionDTO();
		responseTransactionDTO.setAuthorizationCode(getRandomNumberString());
		
		try {
			validateTransaction(requestTransactionDTO);
			
			responseTransactionDTO.setAction(requestTransactionDTO.getAction());
			
			Card card = cardService.findByCardNumber(requestTransactionDTO.getCardNumber());

			if (Action.WITHDRAW.getValor().equals(requestTransactionDTO.getAction()) && card.getAvailableAmount().compareTo(convert(requestTransactionDTO.getAmount())) == -1) {
				responseTransactionDTO.setCode("51");
			} else {
				Transaction transaction = buildTransaction(requestTransactionDTO, card);
				
				updateCardAmount(card, requestTransactionDTO.getAction(), transaction.getId().getAmount());
				
				this.transactionService.merge(transaction);
				
				responseTransactionDTO.setCode("00");
			}
		} catch (FindException e) {
			responseTransactionDTO.setCode("14");
		} catch (Exception e) {
			responseTransactionDTO.setCode("96");
		} finally {
			return responseTransactionDTO;
		}
	}
	
	private void updateCardAmount(Card card, String action, BigDecimal value) {
		if (Action.DEPOSIT.getValor().equals(action)) {
			card.setAvailableAmount(card.getAvailableAmount().add(value));
		} else if (Action.WITHDRAW.getValor().equals(action)) {
			card.setAvailableAmount(card.getAvailableAmount().subtract(value));
		}
	}

	private Transaction buildTransaction(RequestTransactionDTO requestTransactionDTO, Card card) throws ParseException {
		TransactionId transactionId = new TransactionId();
		transactionId.setDate(LocalDateTime.now());
		transactionId.setAmount(convert(requestTransactionDTO.getAmount()));
		
		Transaction transaction = new Transaction();
		transaction.setId(transactionId);
		transaction.setCard(card);

		return transaction;
	}
	
	private BigDecimal convert(String amount) throws ParseException {
		amount = amount.replaceAll(",",".");
		return new BigDecimal(Double.parseDouble(amount));
	}
	
	private void validateTransaction(RequestTransactionDTO requestTransactionDTO) throws Exception {
		
		String amount = null;
		
		if (StringUtils.isEmpty(requestTransactionDTO.getAmount())) {
			throw new ValidationException("Invalid amount");
		} else {
			amount = requestTransactionDTO.getAmount().replaceAll(",",".");
		}
		
		if (Double.parseDouble(amount) < 0) {
			throw new ValidationException("Negative amount value");
		} else if (!Action.DEPOSIT.getValor().equals(requestTransactionDTO.getAction()) && !Action.WITHDRAW.getValor().equals(requestTransactionDTO.getAction())) {
			throw new ValidationException("Invalid action");
		} else if (StringUtils.isEmpty(requestTransactionDTO.getCardNumber())) {
			throw new ValidationException("Invalid card number");
		}
	}
	
	public String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}
	
}
