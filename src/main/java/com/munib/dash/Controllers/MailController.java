package com.munib.dash.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munib.dash.Services.MailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/mail")
@Slf4j
public class MailController {	
	
//	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());	
	private Throwable currentMethod;
	private MailService mailService;
	
	@Autowired
	public MailController(MailService mailService) {		
		this.currentMethod = new Throwable();
		this.mailService = mailService;
	}
	
	public boolean signUpMessage(String email) throws Exception {		
		log.info("Inside MailController signUpMessage method {}", currentMethod.getStackTrace()[0].getMethodName());
		try {
			Boolean sentMail = mailService.signUpMessage(email);			
			return sentMail;			
		} catch (Exception e) {
			log.error("Error inside MailController signUpMessage {}", e);
			e.printStackTrace();
			return false;
		}
	}

}
