package com.example.mail_send.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mail_send.entity.Email;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {
	@Autowired
	private JavaMailSender maiSender;
	public boolean sendMail(Email email) {
		try {
			MimeMessage mimeMessage = maiSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(email.getUserEmail());
			helper.setSubject(email.getSubject());
			helper.setText(email.getContent());
			maiSender.send(mimeMessage);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
