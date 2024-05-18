package com.example.booking.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.booking.dao.BookingDAO;
import com.example.booking.dto.TicketDTO;
import com.example.booking.model.Email;
import com.example.booking.model.Invoice;
import com.example.booking.model.Ticket;
import com.example.booking.model.User;



@Service
public class BookingService {
	@Autowired
	private BookingDAO bookingDAO;
	
	public void start(long request_id, int user_id, ArrayList<TicketDTO> listTicketDTO) {
		//khoi tao trang thai booking
		bookingDAO.createBookingState(request_id);
		// kiem tra ghe trong
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		boolean checkSeat = true;
		try {
			String checkSeatAvailable = "http://localhost:8085/ticket/check";
			HttpEntity<ArrayList<TicketDTO>> requestEntity = new HttpEntity<>(listTicketDTO, headers);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
					checkSeatAvailable,
					HttpMethod.POST,
					requestEntity,
					Boolean.class);
			checkSeat = responseEntity.getBody();
		}
		catch (Exception e) {
			// ghe khong con trong
			e.printStackTrace();
			for(int i = 1; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
			return;
		}
		bookingDAO.updateBookingState(request_id, 1, 1);
		// lay thong tin nguoi dung
		User user = null;
		try {
			String getUser = "http://localhost:8085/user?user_id=" + user_id;
			ResponseEntity<User> responseEntity2 = restTemplate.getForEntity(getUser, User.class);
			user = responseEntity2.getBody();
		}
		catch (Exception e) {
			// khong lay dc thong tin nguoi dung
			e.printStackTrace();
			for(int i = 2; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
			return;
		}
		bookingDAO.updateBookingState(request_id, 2, 1);
		//khoi tao hoa don
		Invoice invoice = null;
		try {
			String createInvoice = "http://localhost:8085/invoice?user_id=" + user_id;
			ResponseEntity<Invoice> responseEntity3 = restTemplate.exchange(
					createInvoice, 
					HttpMethod.POST,
					null,
					Invoice.class);
			invoice = responseEntity3.getBody();
		}
		catch (Exception e) {
			// khong tao dc hoa don
			e.printStackTrace();
			for(int i = 3; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
			return;
		}
		if(invoice == null || invoice.getId() == 0) {
			// khong tao dc hoa don
			for(int i = 3; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
			return;
		}
		bookingDAO.updateBookingState(request_id, 3, 1);
		// tao ve
		ArrayList<Ticket> listTicket = null;
		try {
			String createTicket = "http://localhost:8085/ticket/create?invoice_id=" + invoice.getId();
			HttpEntity<ArrayList<TicketDTO>> requestEntity2 = new HttpEntity<>(listTicketDTO, headers);
			ResponseEntity<ArrayList<Ticket>> responseEntity4 = restTemplate.exchange(
					createTicket,
					HttpMethod.POST,
					requestEntity2,
					new ParameterizedTypeReference<ArrayList<Ticket>>() {});
			listTicket = responseEntity4.getBody();
		} 
		catch (Exception e) {
			// khong tao dc ve
			e.printStackTrace();
			for(int i = 4; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
			return;
		}
		bookingDAO.updateBookingState(request_id, 4, 1);
		double totalPrice = 0;
		for(Ticket t : listTicket) {
			totalPrice += t.getPrice();
		}
		//kiem tra thanh toan
		boolean checkPayment = false;
		if(totalPrice <= user.getAmount()) {
			checkPayment = true;
			user.setAmount(user.getAmount() - totalPrice);
			bookingDAO.updateBookingState(request_id, 5, 1);
		}
		else {
			bookingDAO.updateBookingState(request_id, 5, -1);
		}
		if(checkPayment) {
			boolean checkUpdateAmount = false;
			try {
				String updateAmount = "http://localhost:8085/user";
				HttpEntity<User> requestEntity3 = new HttpEntity<>(user, headers);
				ResponseEntity<Boolean> responseEntity5 = restTemplate.exchange(
						updateAmount, 
						HttpMethod.PUT,
						requestEntity3,
						Boolean.class);
				checkUpdateAmount = responseEntity5.getBody();
			} 
			catch (Exception e) {
				// khong cap nhat dc so tien
				e.printStackTrace();
				for(int i = 6; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
				return;
			}
			bookingDAO.updateBookingState(request_id, 6, 1);
		}
		else {
			try {
				deleteTicket(invoice.getId());
			}
			catch(Exception e) {
				// khong xoa dc ve
				e.printStackTrace();
				for(int i = 6; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
				return;
			}
			bookingDAO.updateBookingState(request_id, 6, 1);
		}
		try {
			if(checkPayment) updateInvoiceStatus(invoice.getId(), 1);
			else updateInvoiceStatus(invoice.getId(), -1);
		} 
		catch (Exception e) {
			// khong cap nhat dc hoa don
			e.printStackTrace();
			for(int i = 7; i <= 8; i++) bookingDAO.updateBookingState(request_id, i, -1);
			return;
		}
		bookingDAO.updateBookingState(request_id, 7, 1);
		try {
			sendMail(user, checkPayment, invoice.getId());
		}
		catch(Exception e) {
			// khong gui dc mail
			e.printStackTrace();
			bookingDAO.updateBookingState(request_id, 8, -1);
		}
		bookingDAO.updateBookingState(request_id, 8, 1);
	}
	private void deleteTicket(int invoice_id) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		String deleteTicket = "http://localhost:8085/ticket?invoice_id=" + invoice_id;
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
				deleteTicket,
				HttpMethod.DELETE,
				null,
				Boolean.class); 
		boolean check = responseEntity.getBody();
		if(!check) throw new Exception();
	}
	private void updateInvoiceStatus(int invoice_id, int status) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String deleteTicket = "http://localhost:8085/invoice?invoice_id=" + invoice_id + "&status=" + status;
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
				deleteTicket,
				HttpMethod.PUT,
				null,
				Boolean.class); 
		boolean check = responseEntity.getBody();
		if(!check) throw new Exception();
	}
	private void sendMail(User user, boolean status, int invoice_id) throws Exception {
		Email email = new Email();
		email.setUserEmail(user.getEmail());
		if(status) {
			email.setSubject("Đặt vé xem phim thành công");
			email.setContent("Quý khách đã đặt vé thành công, ID hóa đơn = " + invoice_id + ".\nVui lòng tới rạp và đưa ra email này để lấy vé!");
		}
		else{
			email.setSubject("Đặt vé xem phim thất bại");
			email.setContent("Quý khách đặt vé thất bại do tài khoản không đủ.\nVui lòng nạp thêm tiền!");
		}
		RestTemplate restTemplate = new RestTemplate();
		String sendMail = "http://localhost:8085/mail";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Email> requestEntity = new HttpEntity<>(email, headers);
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
				sendMail, 
				HttpMethod.POST,
				requestEntity,
				Boolean.class);
		boolean check = responseEntity.getBody();
		if(!check) throw new Exception();
	}
	
	
	public ArrayList<Integer> getBookingState(long request_id){
		return bookingDAO.getBookingState(request_id);
	}
}
