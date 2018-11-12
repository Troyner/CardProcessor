package br.com.hubfintech.CardProcessor.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class TransactionDTO {

	@JsonProperty(value="date")
	private String date;
	
	@JsonProperty(value="amount")
	private BigDecimal amount;
	
}
