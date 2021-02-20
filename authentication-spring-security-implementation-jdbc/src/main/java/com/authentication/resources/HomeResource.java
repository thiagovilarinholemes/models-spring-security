package com.authentication.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

	@GetMapping("/")
	public String  home() {
		return "<h1>Bem a Home!</h1>";
	}
	
	@GetMapping("/admin")
	public String  admin() {
		return "<h1>Bem Admin!</h1> \n "
				+ "<h1>Rota Admin!</h1>";
	}
	
	@GetMapping("/user")
	public String  user() {
		return "<h1>Bem User e Admin!</h1> \n"
				+ "<h1>Rota User e Admin!</h1>";
	}
}
