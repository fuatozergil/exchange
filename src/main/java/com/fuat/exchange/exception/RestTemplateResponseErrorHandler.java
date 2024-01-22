package com.fuat.exchange.exception;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.core.log.LogFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return new DefaultResponseErrorHandler().hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatusCode statusCode = response.getStatusCode();
		handleError(response, statusCode);
	}

	public void handleError(ClientHttpResponse httpResponse, HttpStatusCode statusCode) throws IOException {

		String statusText = httpResponse.getStatusText();
		HttpHeaders headers = httpResponse.getHeaders();
		byte[] body = getResponseBody(httpResponse);
		Charset charset = getCharset(httpResponse);
		String message = getErrorMessage(statusCode.value(), statusText, body, charset);
		RestClientResponseException ex;
		if (statusCode.is4xxClientError()) {
			ex = ExchangeClientException.create(message, statusCode, statusText, headers, body, charset);
		} else if (statusCode.is5xxServerError()) {
			ex = ExchangeServerError.create(message, statusCode, statusText, headers, body, charset);
		} else {
			ex = new UnknownHttpStatusCodeException(message, statusCode.value(), statusText, headers, body, charset);
		}
		log.error(ex.getMessage());
		throw ex;
	}

	private String getErrorMessage(int rawStatusCode, String statusText, @Nullable byte[] responseBody,
			@Nullable Charset charset) {

		String preface = rawStatusCode + " " + statusText + ": ";

		if (ObjectUtils.isEmpty(responseBody)) {
			return preface + "[no body]";
		}

		charset = (charset != null ? charset : StandardCharsets.UTF_8);

		String bodyText = new String(responseBody, charset);
		bodyText = LogFormatUtils.formatValue(bodyText, -1, true);

		return preface + bodyText;
	}

	protected byte[] getResponseBody(ClientHttpResponse response) {
		try {
			return FileCopyUtils.copyToByteArray(response.getBody());
		} catch (IOException ex) {
		}
		return new byte[0];
	}

	@Nullable
	protected Charset getCharset(ClientHttpResponse response) {
		HttpHeaders headers = response.getHeaders();
		MediaType contentType = headers.getContentType();
		return (contentType != null ? contentType.getCharset() : null);
	}

}