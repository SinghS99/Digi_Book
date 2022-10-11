package com.digitalbooks.book.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_base_data")
public class BookDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int bookId;
	private String title;
	private String category;
	private String image;
	private double price;
	private String publisher;
	private int active;
	private String content;
	private String authorName;
	@Column(name = "time_stamp")
	private long timeStamp;
	@Column(name = "author_profileid")
	private int authorProfileId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getAuthorProfileId() {
		return authorProfileId;
	}

	public void setAuthorProfileId(int authorProfileId) {
		this.authorProfileId = authorProfileId;
	}

	public BookDetails(int bookId, String title, String category, String image, double price, String publisher,
			int active, String content, String authorName, long timeStamp, int authorProfileId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.category = category;
		this.image = image;
		this.price = price;
		this.publisher = publisher;
		this.active = active;
		this.content = content;
		this.authorName = authorName;
		this.timeStamp = timeStamp;
		this.authorProfileId = authorProfileId;
	}

	public BookDetails() {
	}

}
