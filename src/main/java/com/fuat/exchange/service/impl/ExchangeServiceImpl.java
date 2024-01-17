package com.fuat.exchange.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fuat.exchange.enums.TransactionTypes;
import com.fuat.exchange.model.ConversionResponse;
import com.fuat.exchange.model.RatesResponse;
import com.fuat.exchange.model.TransactionResponse;
import com.fuat.exchange.model.entity.Transaction;
import com.fuat.exchange.repository.TransactionRepository;
import com.fuat.exchange.service.ExchangeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

	private final RestTemplate restTemplate;

	private final TransactionRepository transactionRepository;

	@Value("${fixerapi.latest-url}")
	private String url;
	
	private String base_currency= "EUR";

	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		log.info("saveTransaction transaction", transaction);
	}

	// EUR based because of fixerApi restrictions
	private RatesResponse getExchangeRateEuroBase(String currency) throws Exception {
		HttpEntity<RatesResponse> entity = restTemplate.getForEntity(url + currency, RatesResponse.class);
		RatesResponse ratesResponse = entity.getBody();
		if (ratesResponse == null) {
			throw new Exception("ratesResponse is null");
		}
		log.info("getExchangeRateEuroBase response:", ratesResponse.toString());
		return ratesResponse;
	}

	// EUR based because of fixerApi restrictions
	private ConversionResponse getExchangeConversionEuroBase(BigDecimal amount, String currency) throws Exception {
		LocalDateTime now = LocalDateTime.now();

		RatesResponse ratesResponse = getExchangeRateEuroBase(currency);
		if (ratesResponse == null) {
			throw new Exception("ratesResponse is null");
		}
		BigDecimal conversionResultAmount = amount.multiply(ratesResponse.getRates().get(currency));
		
		Transaction transaction = new Transaction(amount,base_currency,currency,conversionResultAmount,TransactionTypes.CONVERSION,now);
		saveTransaction(transaction);
		
		ConversionResponse conversionResponse = new ConversionResponse(transaction.id,now ,base_currency,currency,conversionResultAmount);

		log.info("getExchangeConversionEuroBase conversionResponse:", conversionResponse);

		return conversionResponse;
	}

	@Override
	public TransactionResponse getExchangeConversionHistory(Long transactionId) throws Exception {

		TransactionResponse transactionResponse = null;
		transactionResponse = new TransactionResponse(transactionRepository.findById(transactionId).orElseThrow());
		return transactionResponse;
	}

	@Override
	public RatesResponse getExchangeRate(String currency, String currencyConvertTo) throws Exception {
		// Call EUR based because of fixerApi restrictions
		return getExchangeRateEuroBase(currencyConvertTo);
	}

	@Override
	public ConversionResponse getExchangeConversion(BigDecimal amount, String currency, String currencyConvertTo)
			throws Exception {
		// Call EUR based because of fixerApi restrictions
		return getExchangeConversionEuroBase(amount, currencyConvertTo);
	}

}
