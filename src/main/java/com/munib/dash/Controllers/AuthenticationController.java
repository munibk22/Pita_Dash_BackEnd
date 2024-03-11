package com.munib.dash.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munib.dash.Services.AuthenticationService;
import com.munib.dash.Services.MailService;
import com.munib.dash.models.CustomerModel;
import com.munib.dash.models.LoginResponseDTO;
import com.munib.dash.models.RegistrationDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticationController {
	
	private AuthenticationService authenticationService;
	private MailService mailService;
	private Throwable currentMethod;
	
	@Autowired
	public AuthenticationController(MailService mailService, AuthenticationService authenticationService) {
		this.mailService = mailService;
		this.authenticationService = authenticationService;
		this.currentMethod = new Throwable();
	}

	@PostMapping(value = "/register")
	public CustomerModel registerCustomer(@RequestBody RegistrationDTO registrationBody) throws Exception {
		try {
			log.info("Inside AuthenticationController method:  {}", currentMethod.getStackTrace()[0].getMethodName());
			mailService.signUpMessage(registrationBody.getUserName(),registrationBody.getFirstName());			
			return authenticationService.registerCustomer(registrationBody);
		} catch (Exception e) {
			log.error("Error inside AuthenticationController {}", e);
			e.printStackTrace();
			return null;	
		}		
	};
	
	@PostMapping(value = "/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body)throws Exception{
		log.info("Inside AuthenticationController method:  {}", currentMethod.getStackTrace()[0].getMethodName());
        return authenticationService.loginUser(body.getUserName(), body.getPassword());
    }	
	
}
