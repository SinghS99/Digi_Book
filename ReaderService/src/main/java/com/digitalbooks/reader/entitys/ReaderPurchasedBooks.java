package com.digitalbooks.reader.entitys;

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
	@Column(name = "time_stamp")
	private long timeStamp;
	private String paymentId;
	@Column(name = "subscription_Status")
	private int subscriptionStatus;

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

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public int getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(int subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ReaderPurchasedBooks(int bookId, String readerName, String emailId, long timeStamp, String paymentId,
			int subscriptionStatus) {
		super();
		this.bookId = bookId;
		this.readerName = readerName;
		this.emailId = emailId;
		this.timeStamp = timeStamp;
		this.paymentId = paymentId;
		this.subscriptionStatus = subscriptionStatus;
	}

	public ReaderPurchasedBooks() {
	}

}
