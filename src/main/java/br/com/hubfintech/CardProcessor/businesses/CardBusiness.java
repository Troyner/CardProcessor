package br.com.hubfintech.CardProcessor.businesses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hubfintech.CardProcessor.dtos.CardDTO;
import br.com.hubfintech.CardProcessor.exceptions.FindException;
import br.com.hubfintech.CardProcessor.services.CardService;

/**
 * Classe responsável por ser a implementação de
 * negócio dos processos relacionados ao Relatório
 * de Desvio de Comportamento.
 * @author marcus.martins
 * @since 13/03/2018 
 */
@Component
public class CardBusiness {
	
	@Autowired
	private CardService cardService;

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
