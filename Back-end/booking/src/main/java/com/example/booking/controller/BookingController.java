package com.example.booking.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.dto.TicketDTO;
import com.example.booking.service.BookingService;

@RestController
@RequestMapping("/start")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping()
	public void booking(@RequestParam(value = "request_id", required = true) long request_id,
							@RequestParam(value = "user_id", required = true) int user_id, 
							@RequestBody ArrayList<TicketDTO> listTicketDTO){
		bookingService.start(request_id, user_id, listTicketDTO);
	}
	@GetMapping
	public ResponseEntity<ArrayList<Integer>> getBookingState(@RequestParam(value = "request_id", required = true) long request_id){
		return new ResponseEntity<>(bookingService.getBookingState(request_id), HttpStatus.OK);
	}
}
