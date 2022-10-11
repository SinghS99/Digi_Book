package com.author.exceptionhandler;

@SuppressWarnings("serial")
public class AuthorExceptionHandler extends Exception {

	public AuthorExceptionHandler() {
	}

	public AuthorExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorExceptionHandler(String message) {
		super(message);
	}

	public AuthorExceptionHandler(Throwable cause) {
		super(cause);
	}

	public AuthorExceptionHandler(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
