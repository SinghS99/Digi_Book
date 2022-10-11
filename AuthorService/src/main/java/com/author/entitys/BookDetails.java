package com.author.entitys;

import java.time.LocalDate;
import java.time.LocalTime;

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
	private LocalDate date = LocalDate.now();
	private LocalTime time = LocalTime.now();
	private int authorProfileId;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public BookDetails() {

	}

	public int getAuthorProfileId() {
		return authorProfileId;
	}

	public void setAuthorProfileId(int authorProfileId) {
		this.authorProfileId = authorProfileId;
	}

	public BookDetails(int bookId, String title, String category, String image, double price, String publisher,
			int active, String content, String authorName, LocalDate date, LocalTime time, int authorProfileId) {
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
		this.date = date;
		this.time = time;
		this.authorProfileId = authorProfileId;
	}

}
