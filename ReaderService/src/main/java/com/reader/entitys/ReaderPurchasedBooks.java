package com.reader.entitys;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_purchased_books")
public class ReaderPurchasedBooks {

	@Id
	@Column(name = "book_id")
	private int bookId;
	private String readerName;
	@Column(name = "email_id")
	private String emailId;
	private LocalDate date = LocalDate.now();
	private LocalTime time = LocalTime.now();
	private String paymentId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public ReaderPurchasedBooks(int bookId, String readerName, String emailId, LocalDate date, LocalTime time,
			String paymentId) {
		super();
		this.bookId = bookId;
		this.readerName = readerName;
		this.emailId = emailId;
		this.date = date;
		this.time = time;
		this.paymentId = paymentId;
	}

	public ReaderPurchasedBooks() {
	}

}
