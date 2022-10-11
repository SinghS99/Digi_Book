package com.author.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_account_details")
public class AuthorDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_profileid")
	private int authorProfileId;
	private String authorName;
	private String emailId;
	private String password;
	private String jwtToken;

	public int getAuthorProfileId() {
		return authorProfileId;
	}

	public void setAuthorProfileId(int authorProfileId) {
		this.authorProfileId = authorProfileId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public AuthorDetails(String authorName, String emailId, String password) {
		super();
		this.authorName = authorName;
		this.emailId = emailId;
		this.password = password;
	}

	public AuthorDetails(int authorProfileId, String authorName, String emailId, String jwtToken) {
		super();
		this.authorProfileId = authorProfileId;
		this.authorName = authorName;
		this.emailId = emailId;
		this.jwtToken = jwtToken;
	}

	public AuthorDetails() {
	}

}
