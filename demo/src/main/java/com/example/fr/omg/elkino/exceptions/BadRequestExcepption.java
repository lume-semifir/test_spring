package com.example.fr.omg.elkino.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExcepption extends RuntimeException {
	public BadRequestExcepption(String message) {
		super(message);
	}
}
