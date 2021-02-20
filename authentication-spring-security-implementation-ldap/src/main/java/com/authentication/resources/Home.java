package com.authentication.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@GetMapping("/")
	public String index() {
		return "<h1>Home Page</h1>";
	}
}
