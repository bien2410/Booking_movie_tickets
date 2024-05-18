package com.example.ticket.request;

public class TicketDTO {
	private int seat_id;
	private int showtime_id;
	
	public TicketDTO() {
		super();
	}

	public TicketDTO(int seat_id, int showtime_id) {
		super();
		this.seat_id = seat_id;
		this.showtime_id = showtime_id;
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
