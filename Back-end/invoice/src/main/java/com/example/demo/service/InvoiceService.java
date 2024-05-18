package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InvoiceDAO;
import com.example.demo.model.Invoice;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceDAO invoiceDAO;
	
	public Invoice createInvoice(int user_id) {
		return invoiceDAO.createInvoice(user_id);
	}
	
	public boolean updateInvoice(int invoice_id, int status) {
		return invoiceDAO.updateInvoice(invoice_id, status);
	}
	
	public int checkInvoiceStatus(int invoice_id) {
		return invoiceDAO.checkInvoiceStatus(invoice_id);
	}
}
