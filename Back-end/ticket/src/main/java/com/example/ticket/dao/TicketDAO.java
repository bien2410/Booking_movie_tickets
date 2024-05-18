package com.example.ticket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.ticket.entity.Ticket;
import com.example.ticket.request.TicketDTO;

@Repository
public class TicketDAO extends DAO{
	public boolean getTicket(TicketDTO ticketDTO) {
		String sql = "SELECT count(*) FROM ticket WHERE seat_id = ? and showtime_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ticketDTO.getSeat_id());
			ps.setInt(2, ticketDTO.getShowtime_id());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int result = rs.getInt(1);
				return (result == 0) ? false : true;
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return true;
		}
		return true;
	}
	public void createTicket(Ticket ticket) throws SQLException {
		String sql = "INSERT INTO ticket(invoice_id, price, seat_id, showtime_id) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, ticket.getInvoice_id());
		ps.setDouble(2, ticket.getPrice());
		ps.setInt(3, ticket.getSeat_id());
		ps.setInt(4, ticket.getShowtime_id());
		ps.execute();
		ResultSet generatedKey = ps.getGeneratedKeys();
		if(generatedKey.next()) {
			ticket.setId(generatedKey.getInt(1));
		}
	}
	public void deleteTicket(int invoice_id) throws Exception{
		String sql = "DELETE FROM ticket WHERE id != 0 and invoice_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, invoice_id);
		int count = ps.executeUpdate();
		if(count == 0) throw new Exception();
	}
}
