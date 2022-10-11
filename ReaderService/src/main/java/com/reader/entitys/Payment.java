package com.reader.entitys;

public class Payment {

	private String paymentId;
	private String invoiceId;
	private String date;
	private long bookId;
	private String emailId;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailsid(String emailId) {
		this.emailId = emailId;
	}
}
