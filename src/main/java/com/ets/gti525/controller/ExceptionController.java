package com.ets.gti525.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ets.gti525.domain.response.AbstractResponse;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AbstractResponse> handleException(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		System.out.println("Une erreur est survenue pendant l'appel sur " + request.getRequestURL());
		AbstractResponse response = new AbstractResponse(HttpStatus.INTERNAL_SERVER_ERROR) {};
		return ResponseEntity.status(response.getStatus()).build();
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<AbstractResponse> handleAccessDeniedException(Exception ex) {
		AbstractResponse response = new AbstractResponse(HttpStatus.FORBIDDEN) {};
		return ResponseEntity.status(response.getStatus()).build();
	}
}
