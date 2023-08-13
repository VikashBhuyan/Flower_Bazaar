package com.flower.vikash.exception;

public class AuthenticationException extends RuntimeException {

	public AuthenticationException() {
		
	}
	public AuthenticationException(String msg) {
		super(msg);
	}
}
