package com.woomin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "todolist";
	}
}
