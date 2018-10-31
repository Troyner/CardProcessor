package br.com.hubfintech.CardProcessor.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDTO {

	@JsonProperty(value="cardnumber")
	private String cardNumber;
	
	@JsonProperty(value="availableAmount")
	private BigDecimal availableAmount;
	
	@JsonProperty(value="transactions")
    private List<TransactionDTO> transactions;
	
}
