package br.com.hubfintech.CardProcessor.businesses;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.dtos.TransactionDTO;
import br.com.hubfintech.CardProcessor.exceptions.FindException;
import br.com.hubfintech.CardProcessor.services.CardService;

@RunWith(MockitoJUnitRunner.class)
public class CardBusinessTest {

	private static final Logger LOGGER = Logger.getLogger(CardBusinessTest.class);
	
	@Mock
	private CardService cardService;
	
	@InjectMocks
	private CardBusiness cardBusiness;
	
	private String mockCardNumber = "4111111111111111";
	
	@Test
	public void testFindCardTransactions() {
		try {
			CardDTO mockCardDTO = this.mockCardDTO();
			
			when(cardService.findCardTransactions(mockCardNumber, 10)).thenReturn(mockCardDTO);
			assertEquals(mockCardDTO, cardBusiness.findCardTransactions(mockCardNumber));
		} catch (FindException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Test
	public void testFindCardTransactionsException() {
		try {
			when(cardService.findCardTransactions(mockCardNumber, 10)).thenThrow(new FindException("Card not found"));
			fail("Card not found", cardBusiness.findCardTransactions(mockCardNumber));
		} catch (FindException e) {
			LOGGER.info("Test performed successfully");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private CardDTO mockCardDTO() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		List<TransactionDTO> transactions = new ArrayList<>();
		
		for (short i = 0; i < 10; i++) {
			transactions.add(TransactionDTO.builder()
				.amount(BigDecimal.valueOf(Math.random()))
				.date(formatter.format(LocalDateTime.now().minusDays(i)))
				.build()
			);
		}
		
		return CardDTO.builder()
				.cardNumber(mockCardNumber)
				.availableAmount(BigDecimal.valueOf(Math.random()))
				.transactions(transactions)
				.build();
	}
	
}
