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
	
	public boolean signUpMessage(String email, String firstName) throws Exception {
		log.info("Inside MailController signUpMessage method {}", currentMethod.getStackTrace()[0].getMethodName());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Welcome to PitaDash! - 🎉");
			message.setText("Dear "+firstName + ","
			+"\n\n Welcome to Pita Dash - 🥑, your new favorite destination for Middle Eastern "
			+ "and Pakistani food! We're thrilled to have you on board."
			+"\n\n We're committed to providing you with a seamless food delivery"
			+ " to your doorstep."				
			+ "\n If you have any questions, "
			+ "please don't hesitate to reach out to us at skkitchenstore@gmail.com."
			+"\n\n ♥ Thank you for signing up,"
			+"\n-Sahar ♥"					
			);
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
