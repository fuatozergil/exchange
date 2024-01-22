package com.fuat.exchange.exception;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class ExchangeServerError extends HttpClientErrorException {

	private static final long serialVersionUID = -177716254822182844L;

	public ExchangeServerError(String message, HttpStatusCode statusCode, String statusText, HttpHeaders headers,
			byte[] body, Charset responseCharset) {
		super(message, statusCode, statusText, headers, body, responseCharset);
	}

}
