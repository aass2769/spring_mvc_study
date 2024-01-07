package com.woomin.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test1")
	public String test1(Model model) {
		
//		int[] array1 = {10, 20, 30};
//		//model.addAttribute("array1", array1[0]);
//		model.addAttribute("array1", array1[10]);
		
		ArrayList<String> list = null;
		list.add("문자열");
		
		return "test1";
	}
	
	//@ExceptionHandler어노테이션을 사용하고 오류를 설정하면 해당 오류가 발생했을 시 메서드가 실행됨.
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	public String exception1() {
		return "error1";
	}
}
