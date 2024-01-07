package com.woomin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/todolist")
	public String to_do_list() {
		
	
		
		
		
		return "todolost";
	}
}
