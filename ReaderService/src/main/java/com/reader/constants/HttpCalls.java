package com.reader.constants;

public class HttpCalls {

	private final static String BOOK = "http://localhost:8090/book/";
	public static final String SEARCHBOOK = BOOK + "searchbooks?title=%s&author_name=%s&publisher=%s&date=%s";

}
