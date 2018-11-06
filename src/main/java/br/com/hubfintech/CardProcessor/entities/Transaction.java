package br.com.hubfintech.CardProcessor.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private TransactionId id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="CARD_ID", nullable=false)
    private Card card;
	
}
