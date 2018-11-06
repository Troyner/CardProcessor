package br.com.hubfintech.CardProcessor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {

	@JsonProperty(value="date")
	private String date;
	
	@JsonProperty(value="amount")
	private String amount;
	
}
