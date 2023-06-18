package com.munib.dash.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munib.dash.Services.AuthenticationService;
import com.munib.dash.models.CustomerModel;
import com.munib.dash.models.LoginResponseDTO;
import com.munib.dash.models.RegistrationDTO;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(value = "/register")
	public CustomerModel registerCustomer(@RequestBody RegistrationDTO registrationBody) {
		return authenticationService.registerCustomer(				
				registrationBody.getUserName(),
				registrationBody.getPassword(),
				registrationBody.getFirstName(),
				registrationBody.getLastName(),
				registrationBody.getAddress(),
				registrationBody.getZipCode(),
				registrationBody.getPhone(),
				registrationBody.getId()
				);	
		
	};
	
	@PostMapping(value = "/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUserName(), body.getPassword());
    }	
	
}
