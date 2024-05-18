package com.example.mail_send.entity;

public class Email {
	private String userEmail;
	private String subject;
	private String content;
	public Email() {
		super();
	}
	public Email(String userEmail, String subject, String content) {
		super();
		this.userEmail = userEmail;
		this.subject = subject;
		this.content = content;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
