package com.example.booking.model;

public class Ticket {
	private int id;
	private int invoice_id;
	private double price;
	private int seat_id;
	private int showtime_id;
	public Ticket() {
		super();
	}
	public Ticket(int id, int invoice_id, double price, int seat_id, int showtime_id) {
		super();
		this.id = id;
		this.invoice_id = invoice_id;
		this.price = price;
		this.seat_id = seat_id;
		this.showtime_id = showtime_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getShowtime_id() {
		return showtime_id;
	}
	public void setShowtime_id(int showtime_id) {
		this.showtime_id = showtime_id;
	}
	
}
