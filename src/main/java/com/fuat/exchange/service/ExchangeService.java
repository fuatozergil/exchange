package com.fuat.exchange.service;

import java.math.BigDecimal;

import com.fuat.exchange.model.ConversionResponse;
import com.fuat.exchange.model.RatesResponse;
import com.fuat.exchange.model.TransactionResponse;

public interface ExchangeService {

	TransactionResponse getExchangeConversionHistory(Long transactionId) throws Exception;

	RatesResponse getExchangeRate(String currency, String currencyConvertTo) throws Exception;

	ConversionResponse getExchangeConversion(BigDecimal amount, String currency, String currencyConvertTo)
			throws Exception;
}
