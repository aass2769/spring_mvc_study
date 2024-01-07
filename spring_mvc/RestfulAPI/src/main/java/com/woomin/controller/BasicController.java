package com.woomin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller는 html 페이지를 생성하고 반환하는데 사용되며 뷰 렌더링을 위해 주로 jsp,thymeleaf같은 템플릿 엔진과 함께 사용한다.
//HTTP 요청과 관련된 데이터를 처리하고 HTML 또는 다른 뷰 템플릿(jsp등)으로 전달한다.
@Controller
public class BasicController {

	@GetMapping("/test1")
	public String test1() {
		return "test1";
	}
}
