package com.woomin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.woomin.service.UserService;

@RestController
public class RestApiController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		
		boolean cheak = userService.checkUserIdExist(user_id);
		
		//boolean의 return 값을 문자열로 변환하기 위해 +""를 붙임.
		return cheak + "";
	}
}
