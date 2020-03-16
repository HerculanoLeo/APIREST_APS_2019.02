package br.com.aplicacaoaps.apirest.conf.handleErrors;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(status, error, ex));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError api = new ApiError(HttpStatus.BAD_REQUEST);
		api.setMessage("Validation error");
		api.addValidationErrors(ex.getBindingResult().getFieldErrors());
		api.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(api);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex){
		ApiError api = new ApiError(HttpStatus.NOT_FOUND);
		api.setMessage(ex.getMessage());
		return buildResponseEntity(api);
	}
	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	protected ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex){
		String error = "User not found";
		return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, error, ex));
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex){
		String error = "User or Password is incorrect";
		return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, error, ex));
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError api){
		return new ResponseEntity<>(api, api.getStatus());
	}
}
