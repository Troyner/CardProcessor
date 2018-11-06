package br.com.hubfintech.CardProcessor.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseTransactionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value="action")
	private String action;
	
	@JsonProperty(value="code")
	private String code;
	
	@JsonProperty(value="authorization_code")
	private String authorizationCode;
	
}
