package com.fuat.exchange.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fuat.exchange.enums.TransactionTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {

	public Transaction(BigDecimal amount, String currency, String targetCurrency, BigDecimal conversionAmount,
			TransactionTypes transactionType, LocalDateTime date) {
		this.amount = amount;
		this.currency = currency;
		this.targetCurrency = targetCurrency;
		this.conversionAmount = conversionAmount;
		this.transactionType = transactionType;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
	@SequenceGenerator(name = "transaction_seq", allocationSize = 1)
	public Long id;

	@Enumerated(EnumType.STRING)
	public TransactionTypes transactionType;

	public String currency;
	public String targetCurrency;
	public BigDecimal amount;
	public BigDecimal conversionAmount;
	public BigDecimal rate;
	public LocalDateTime date;

}
