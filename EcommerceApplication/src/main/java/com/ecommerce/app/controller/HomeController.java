package com.ecommerce.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/welcome")
	public String welcome() {
		String s="this is private page";
		s+="this page is not allowed unauthorized users";
		return s;
	}
	
	@GetMapping("/getuser")
	public String getUser() {
		return 	"{\"name\":\"Nitesh\"}";
	}

}
