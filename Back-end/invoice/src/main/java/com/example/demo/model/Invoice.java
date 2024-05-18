package com.example.demo.model;

public class Invoice {
	private int id;
	private int user_id;
	private int status;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Invoice(int id, int user_id, int status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Invoice() {
		super();
	}

}
