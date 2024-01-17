package com.fuat.exchange.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {

	private Long transactionId;
	private LocalDateTime date;
	private String currency;
	private String targetCurrency;
	private BigDecimal amount;

}
