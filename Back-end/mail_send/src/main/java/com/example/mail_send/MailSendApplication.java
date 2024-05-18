package com.example.mail_send;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MailSendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSendApplication.class, args);
	}

}
