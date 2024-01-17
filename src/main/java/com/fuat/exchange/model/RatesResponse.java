package com.fuat.exchange.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatesResponse {

	private boolean success;
	private Long timestamp;
	private String base;
	private String date;
	private Map<String, BigDecimal> rates;

}
