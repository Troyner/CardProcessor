package br.com.hubfintech.CardProcessor.businesses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.exceptions.FindException;
import br.com.hubfintech.CardProcessor.services.CardService;

/**
 *Class responsible for being the business implementation of processes related to Card.
 * @author marcus.martins
 */
@Component
public class CardBusiness {
	
	@Autowired
	private CardService cardService;

	/**
	 * Find card and its transactions by card number, delimiting by 10.
	 * @param cardNumber
	 * @return CardDTO
	 * @throws FindException
	 */
	public CardDTO findCardTransactions(String cardNumber) throws FindException {
		try {
			return this.cardService.findCardTransactions(cardNumber, 10);
		} catch (FindException e) {
			throw e;
		} catch (Exception e) {
			throw new FindException("Unknown error at finding card", e);
		}
	}
	
}
