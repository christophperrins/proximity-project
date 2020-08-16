package uk.co.bpdts.proximity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class BaseException extends RuntimeException {

	private ExceptionObject body;

	private MultiValueMap<String, String> headers;

	private HttpStatus status;
	
	public BaseException(String message, HttpStatus status) {
		this(message, null, status);
	}

	public BaseException(String message, MultiValueMap<String, String> headers, HttpStatus status) {
		this.status = status;
		this.headers = headers;
		setBody(message);
	}	
	
	public ExceptionObject getBody() {
		return body;
	}


	public void setBody(ExceptionObject body) {
		this.body = body;
	}


	public MultiValueMap<String, String> getHeaders() {
		return headers;
	}


	public void setHeaders(MultiValueMap<String, String> headers) {
		this.headers = headers;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	private void setBody(String message) {
		ExceptionObject exceptionObject = new ExceptionObject();
		ExceptionObjectContents error = new ExceptionObjectContents();
		error.setMessage(message);
		error.setStatus(this.status.value());
		exceptionObject.setError(error);
		this.body = exceptionObject;
	}
}
