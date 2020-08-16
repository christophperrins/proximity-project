package uk.co.bpdts.proximity.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProximityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ExceptionObject> handleException(BaseException baseException) {
		return new ResponseEntity<ExceptionObject>(baseException.getBody(), baseException.getHeaders(), baseException.getStatus());
	}
}
