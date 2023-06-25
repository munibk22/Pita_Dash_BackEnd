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
	
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping
	public String home() {
		System.out.println("Welcome to the Home Page");
		return "Welcome to the Home Page2";
	}
	@GetMapping(value="customer")
	public String adminHello(CustomerModel user) {
		System.out.println("Hello From Customer Controller");
		return "Hello From Customer Controller";
//		return customerService.loadUserByUsername(user.getUsername());
	}
	
	@GetMapping(value="customer/findAll")
	public List<CustomerModel> findAll() throws SQLException {
		return customerService.findAll();
	}

}
