package com.fuat.exchange.exception;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class ExchangeClientException extends HttpClientErrorException {

	private static final long serialVersionUID = -1710715054402420267L;

	public ExchangeClientException(String message, HttpStatusCode statusCode, String statusText, HttpHeaders headers,
			byte[] body, Charset responseCharset) {
		super(message, statusCode, statusText, headers, body, responseCharset);
	}

}
