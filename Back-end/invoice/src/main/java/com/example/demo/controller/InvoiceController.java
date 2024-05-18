package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
		
	@PostMapping()
	public ResponseEntity<Invoice> createInvoice(@RequestParam(value = "user_id", required = true) int user_id){
		return new ResponseEntity<>(invoiceService.createInvoice(user_id), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Boolean> updateInvoice(@RequestParam(value = "invoice_id", required = true) int invoice_id,
													@RequestParam(value = "status", required = true) int status){
		return new ResponseEntity<>(invoiceService.updateInvoice(invoice_id, status), HttpStatus.OK);
	}
}
