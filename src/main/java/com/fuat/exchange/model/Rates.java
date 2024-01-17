package com.fuat.exchange.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rates {
	
	private String currency;
	private BigDecimal exchangeRate;

}
