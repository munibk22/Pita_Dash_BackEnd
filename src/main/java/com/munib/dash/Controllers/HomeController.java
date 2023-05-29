package com.munib.dash.Controllers;

import java.util.HashSet;

import org.springframework.web.bind.annotation.*;

import com.munib.dash.Services.HomeService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController("/")
@CrossOrigin("*")
public class HomeController {

	HomeService homeService;
	
	@Autowired
	public HomeController(HomeService homeService) throws Exception {
		this.homeService = homeService;
	}

	@GetMapping("hello")
	public String HomeValidate() {
//		HashSet<String> str = new HashSet<>(); 
		String str;
//		str.add("Hello from HomeController");
			str=("Hello from HomeController");
		
//		System.out.println(str);
		return homeService.helloValidate(str);		
	}
}
