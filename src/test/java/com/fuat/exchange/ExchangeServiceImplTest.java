package com.fuat.exchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.fuat.exchange.model.ConversionResponse;
import com.fuat.exchange.model.RatesResponse;
import com.fuat.exchange.model.TransactionResponse;
import com.fuat.exchange.model.entity.Transaction;
import com.fuat.exchange.repository.TransactionRepository;
import com.fuat.exchange.service.impl.ExchangeServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private ExchangeServiceImpl exchangeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		exchangeService = new ExchangeServiceImpl(restTemplate, transactionRepository);
	}

	@Test
	void testGetExchangeConversionHistory() throws Exception {
		Long transactionId = 3L;
		Transaction mockTransaction = new Transaction();
		when(transactionRepository.findById(transactionId)).thenReturn(java.util.Optional.of(mockTransaction));

		TransactionResponse result = exchangeService.getExchangeConversionHistory(transactionId);

		assertEquals(new TransactionResponse(mockTransaction), result);
		verify(transactionRepository, times(1)).findById(transactionId);
	}

	@Test
	void testGetExchangeRate() throws Exception {
		String currency = "EUR";
		String currencyConvertTo = "USD";
		RatesResponse mockRatesResponse = new RatesResponse();
		when(exchangeService.getExchangeRate(currency, currencyConvertTo)).thenReturn(mockRatesResponse);

		assertNotEquals(mockRatesResponse, null);
		verify(exchangeService, times(1)).getExchangeRate(currency, currencyConvertTo);
	}

	@Test
	void testGetExchangeConversion() throws Exception {
		BigDecimal amount = new BigDecimal("100.00");
		String currency = "EUR";
		String currencyConvertTo = "USD";
		ConversionResponse mockConversionResponse = new ConversionResponse();
		when(exchangeService.getExchangeConversion(amount, currency, currencyConvertTo))
				.thenReturn(mockConversionResponse);

		assertNotEquals(mockConversionResponse, null);
		verify(exchangeService, times(1)).getExchangeConversion(amount, currency, currencyConvertTo);
	}
}
