package com.book.exceptionhandler;

@SuppressWarnings("serial")
public class BooksExceptionHandler extends Exception {

	public BooksExceptionHandler() {
	}

	public BooksExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}

	public BooksExceptionHandler(String message) {
		super(message);
	}

	public BooksExceptionHandler(Throwable cause) {
		super(cause);
	}

	public BooksExceptionHandler(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
