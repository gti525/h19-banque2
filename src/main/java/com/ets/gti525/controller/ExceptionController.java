package com.ets.gti525.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ets.gti525.domain.response.AbstractResponse;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AbstractResponse> handleException(HttpServletRequest request) {
		System.out.println("Une erreur est survenue pendant l'appel sur " + request.getRequestURL());
		AbstractResponse response = new AbstractResponse(HttpStatus.INTERNAL_SERVER_ERROR) {};
		return ResponseEntity.status(response.getStatus()).build();
	}
}
