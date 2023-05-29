package com.munib.dash.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/admin")
@CrossOrigin("*")
public class AdminController {

	@GetMapping
	public String adminHello() {
		return "Hello From Admin Controller";
	}
}
