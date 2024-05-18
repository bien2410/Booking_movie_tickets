package com.example.ticket.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket.entity.Ticket;
import com.example.ticket.request.TicketDTO;
import com.example.ticket.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/check")
	public ResponseEntity<Boolean> checkSeatAvaiable(@RequestBody ArrayList<TicketDTO> listTicketDTO){
		boolean result = ticketService.checkSeatAvailable(listTicketDTO);
		if(result) return new ResponseEntity<>(result, HttpStatus.OK);
		else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/create")
	public ResponseEntity<ArrayList<Ticket>> createTicket(@RequestParam(value = "invoice_id", required = true) int invoice_id, @RequestBody ArrayList<TicketDTO> listTicketDTO){
		ArrayList<Ticket> result = ticketService.createTicket(invoice_id, listTicketDTO);
		if(result != null) return new ResponseEntity<>(result, HttpStatus.OK);
		else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping()
	public ResponseEntity<Boolean> deleteTicket(@RequestParam(value = "invoice_id", required = true) int invoice_id){
		boolean result = ticketService.deleteTicket(invoice_id);
		if(result) return new ResponseEntity<>(result, HttpStatus.OK);
		else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
