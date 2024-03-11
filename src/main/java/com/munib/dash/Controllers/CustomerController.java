package com.munib.dash.Controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munib.dash.Services.CustomerService;
import com.munib.dash.models.CustomerModel;

@RestController
@RequestMapping(value="/")
@CrossOrigin("*")
public class CustomerController {	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping(value="customer")
	public String adminHello(CustomerModel user) throws Exception {
		System.out.println("Hello From Customer Controller");
		return "Hello From Customer Controller";
//		return customerService.loadUserByUsername(user.getUsername());
	}	

}
