package com.munib.dash.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailService {
	
	private Throwable currentMethod;
	private JavaMailSender javaMailSender;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		this.currentMethod = new Throwable();
	}
	
	public boolean signUpMessage(String email) throws Exception {
		log.info("Inside MailController signUpMessage method {}", currentMethod.getStackTrace()[0].getMethodName());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Welcome to PitaDash! - 🎉");
			message.setText("Hello From PitaDash! - 🥑 "+
			"\n ♥ Thank you for signing up ♥");
			javaMailSender.send(message);
			log.info("Inside MailController signUpMessage method {}", email);
			
			return true;
		} catch (Exception e) {
			log.error("Error inside MailController signUpMessage {}", e);
			e.printStackTrace();
			return false;
		}
	}

}
