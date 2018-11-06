package br.com.hubfintech.CardProcessor.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class TransactionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DATE")
	private LocalDateTime date;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
}
