package com.example.demo.model;

public class User {
	private int id;
	private String username, password, email;
	private double amount;
	public User() {
		super();
	}

	public User(int id, String username, String password, String email, double amount) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}