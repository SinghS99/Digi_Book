package com.author.constants;

public class HttpCalls {

	private final static String BOOK = "http://localhost:8090/book/";
	public static final String GETALLBOOKSFORAUTHOR = BOOK + "getallbooksforauthor?emailId=";
	public static final String BLOCKBOOK = BOOK + "blockBook?bookId=";
	public static final String PUBLISHBOOK = BOOK + "publishbook";

}
