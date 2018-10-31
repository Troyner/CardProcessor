package br.com.hubfintech.CardProcessor.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name = "TRANSACTION_ID")
	private Long id;
	
	@Column(name = "DATE")
	private LocalDateTime date;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@ManyToOne
    @JoinColumn(name="CARD_ID", nullable=false)
    private Card card;
	
}
