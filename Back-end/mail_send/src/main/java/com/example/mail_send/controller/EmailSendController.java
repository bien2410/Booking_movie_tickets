package com.example.mail_send.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mail_send.entity.Email;
import com.example.mail_send.service.EmailSendService;

@RestController
@RequestMapping("/mail")
public class EmailSendController {
	@Autowired
	private EmailSendService emailSendService;
	
	@PostMapping()
	public ResponseEntity<Boolean> sendMail(@RequestBody Email email) {
		boolean result = emailSendService.sendMail(email);
		if(result) return new ResponseEntity<>(result, HttpStatus.OK);
		else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
