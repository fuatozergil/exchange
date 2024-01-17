package com.fuat.exchange.api;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuat.exchange.model.ConversionResponse;
import com.fuat.exchange.model.RatesResponse;
import com.fuat.exchange.model.TransactionResponse;
import com.fuat.exchange.service.ExchangeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exchange")
public class ExchangeController {
	
	private final ExchangeService exchangeService;

	@GetMapping("/rate/{currency}/{currencyConvertTo}")
	@Operation(summary = "Gets Exchange Rate", description = "Get Exchange Rate for given currencies")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful response", content = {
			@Content(mediaType = "application/json") }) })
	public RatesResponse exchangeRate(@PathVariable String currency, @PathVariable String currencyConvertTo) throws Exception {
		return exchangeService.getExchangeRate(currency, currencyConvertTo);

	}
	
	@GetMapping("/conversion/{currency}/{currencyConvertTo}/{amount}")
	@Operation(summary = "Converts Currencises with amount", description = "Converts given currencies with given amount")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful response", content = {
			@Content(mediaType = "application/json") }) })
	public ConversionResponse exchangeConversion(@PathVariable String currency, @PathVariable String currencyConvertTo,@PathVariable BigDecimal amount)throws Exception {
		return exchangeService.getExchangeConversion(amount, currency, currencyConvertTo);

	}
	
	@GetMapping("/history/{transactionId}")
	@Operation(summary = "Get Exchange Rate", description = "Get Exchange Rate for given currencies")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful response", content = {
			@Content(mediaType = "application/json") }) })
	public TransactionResponse conversionHistory(@PathVariable Long transactionId)throws Exception {
		return exchangeService.getExchangeConversionHistory(transactionId);

	}

}
