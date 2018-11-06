package br.com.hubfintech.CardProcessor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestTransactionDTO {

	@JsonProperty(value="action")
	private String action;
	
	@JsonProperty(value="cardnumber")
	private String cardNumber;
	
	@JsonProperty(value="amount")
	private String amount;
	
}
