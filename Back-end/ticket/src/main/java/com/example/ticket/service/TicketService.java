package com.example.ticket.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ticket.dao.DAO;
import com.example.ticket.dao.TicketDAO;
import com.example.ticket.entity.Ticket;
import com.example.ticket.request.TicketDTO;

@Service
public class TicketService {
	@Autowired
	private TicketDAO ticketDAO;
	
	public boolean checkSeatAvailable(ArrayList<TicketDTO> listTicketDTO){
		for(TicketDTO ticketDTO : listTicketDTO) {
			if(ticketDAO.getTicket(ticketDTO)) return false;
		}
		return true;
	}
	
	public ArrayList<Ticket> createTicket(int invoice_id, ArrayList<TicketDTO> listTicketDTO) {
		ArrayList<Ticket> result = new ArrayList<>();
		for(TicketDTO ticketDTO : listTicketDTO) {
			Ticket ticket = new Ticket();
			ticket.setInvoice_id(invoice_id);
			ticket.setPrice(70000);
			ticket.setSeat_id(ticketDTO.getSeat_id());
			ticket.setShowtime_id(ticketDTO.getShowtime_id());
			result.add(ticket);
			try {
				ticketDAO.createTicket(ticket);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}
	
	public boolean deleteTicket(int invoice_id) {
		try {
			ticketDAO.deleteTicket(invoice_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
