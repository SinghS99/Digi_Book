package com.digitalbooks.reader.constants;

import java.time.Clock;

public class HttpCalls {

	private final static String BOOK = "http://localhost:8090/book/";
	public static final String SEARCHBOOK = BOOK + "searchbooks?title=%s&author_name=%s&publisher=%s&date=%s";

	public static final long ONEDAYMILLISECONDS = 24 * 60 * 60 * 1000;
	
	public static long getCurrentMilliSeconds() {
		return Clock.systemDefaultZone().millis();
	}
}
