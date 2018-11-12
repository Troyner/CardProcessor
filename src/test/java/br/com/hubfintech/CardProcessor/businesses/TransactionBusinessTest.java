package br.com.hubfintech.CardProcessor.businesses;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.hubfintech.CardProcessor.dtos.RequestTransactionDTO;
import br.com.hubfintech.CardProcessor.dtos.ResponseTransactionDTO;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBusinessTest {

	private static final Logger LOGGER = Logger.getLogger(TransactionBusinessTest.class);
	
	@Autowired
	private TransactionBusiness transactionBusiness;
	
	private String mockCardNumber = "4111111111111111";
	
	@Test
	public void testTransactCode00() {
		try {
			RequestTransactionDTO mockRequestTransactionDTO = this.mockRequestTransactionDTO("DEPOSIT", mockCardNumber, "1000");
			ResponseTransactionDTO responseTransactionDTO = transactionBusiness.transact(mockRequestTransactionDTO);
			assertEquals("00", responseTransactionDTO.getCode());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Test
	public void testTransactCode51() {
		try {
			RequestTransactionDTO mockRequestTransactionDTO = this.mockRequestTransactionDTO("WITHDRAW", mockCardNumber, "1000000");
			ResponseTransactionDTO responseTransactionDTO = transactionBusiness.transact(mockRequestTransactionDTO);
			assertEquals("51", responseTransactionDTO.getCode());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Test
	public void testTransactCode14() {
		try {
			RequestTransactionDTO mockRequestTransactionDTO = this.mockRequestTransactionDTO("DEPOSIT", "4111111111111123", "1000000");
			ResponseTransactionDTO responseTransactionDTO = transactionBusiness.transact(mockRequestTransactionDTO);
			assertEquals("14", responseTransactionDTO.getCode());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Test
	public void testTransactCode96() {
		try {
			RequestTransactionDTO mockRequestTransactionDTO = this.mockRequestTransactionDTO("WITHDRAW", "", "1000000");
			ResponseTransactionDTO responseTransactionDTO = transactionBusiness.transact(mockRequestTransactionDTO);
			assertEquals("96", responseTransactionDTO.getCode());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private RequestTransactionDTO mockRequestTransactionDTO(String action, String cardNumber, String amount) {
		return new RequestTransactionDTO(action, cardNumber, amount);
	}
	
}
