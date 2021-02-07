package com.newgen.serg.pet_shop.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.newgen.serg.pet_shop.exception.DataIsNotValidException;
import com.newgen.serg.pet_shop.exception.InvalidOperationException;
import com.newgen.serg.pet_shop.exception.UnauthorizedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleUnsuspectedException(Exception ex, WebRequest request) {
		String message = "Something went wrong.";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<Object>(new ErrorMessage(message, status), new HttpHeaders(), status);
	}
	
	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
		String message = StringUtils.isBlank(ex.getMessage()) ? "Entity by this data not found." : ex.getMessage();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<Object>(new ErrorMessage(message, status), new HttpHeaders(), status);
	}

	@ExceptionHandler(value = { JsonProcessingException.class })
	public ResponseEntity<Object> handleJsonProcessingException(Exception ex, WebRequest request) {
		String message = "Something went wrong when we try to parse response.";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<Object>(new ErrorMessage(message, status), new HttpHeaders(), status);
	}

	@ExceptionHandler(value = { InvalidOperationException.class })
	public ResponseEntity<Object> handleApplicationCannotDoThis(Exception ex, WebRequest request) {
		String message = StringUtils.isBlank(ex.getMessage()) ? "Server cannot do this." : ex.getMessage();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<Object>(new ErrorMessage(message, status), new HttpHeaders(), status);
	}

	@ExceptionHandler(value = { DataIsNotValidException.class })
	public ResponseEntity<Object> handleDataIsNotValidException(Exception ex, WebRequest request) {
		String message = StringUtils.isBlank(ex.getMessage()) ? "Data is not valid. Check yor request."
				: ex.getMessage();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<Object>(new ErrorMessage(message, status), new HttpHeaders(), status);
	}

	@ExceptionHandler(value = { UnsatisfiedServletRequestParameterException.class })
	public ResponseEntity<Object> handleUnsatisfiedServletRequestParameterException(
            UnsatisfiedServletRequestParameterException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<Object>(new ErrorMessage(ex.getMessage(), status), new HttpHeaders(), status);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                                     WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<Object>(new ErrorMessage(errors, status), new HttpHeaders(), status);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                   WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<Object>(new ErrorMessage(error, status), new HttpHeaders(), status);
	}

	@ExceptionHandler(value = { UnauthorizedException.class })
	public ResponseEntity<Object> handleUnauthorizedException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<Object>(new ErrorMessage(new ArrayList<>(), status), new HttpHeaders(), status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
		String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		HttpStatus st = HttpStatus.NOT_FOUND;
		return new ResponseEntity<Object>(new ErrorMessage(error, st), new HttpHeaders(), st);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		HttpStatus st = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<Object>(new ErrorMessage(error, st), new HttpHeaders(), st);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		HttpStatus st = HttpStatus.METHOD_NOT_ALLOWED;
		return new ResponseEntity<Object>(new ErrorMessage(builder.toString(), st), new HttpHeaders(), st);
	}

}
