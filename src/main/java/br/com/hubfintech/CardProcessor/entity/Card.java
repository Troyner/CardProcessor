package br.com.hubfintech.CardProcessor.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CARD")
public class Card {

	@Id
	@GeneratedValue
	@Column(name = "CARD_ID")
	private Long id;
	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "AVAILABLE_AMOUNT")
	private BigDecimal availableAmount;
	
	@OneToMany(mappedBy = "card", targetEntity = Transaction.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;
	
}
