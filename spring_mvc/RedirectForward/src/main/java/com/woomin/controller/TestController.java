package com.woomin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	//코드의 흐름을 제어할 수 있다.
	//서버가 클라이언트에게 요청할 주소를 응답결과로 전달한다.
	//클라이언트는 응답결과로 받은 요청주소를 요청하게 된다.
	//브라우저가 요청하는것이므로 주소창의 주소가 변경되며 새로운 요청이 발생하는 것이므로
	//HttpServeltRequest 객체는 소멸 후 새롭게 생성되며 HttpSession 객체는 유지된다.
	@GetMapping("/test1")
	public String test1() {
		return "redirect:/sub1";
	}
	
	@GetMapping("/sub1")
	public String sub1() {
		return "sub1";
	}
	
	//코드의 흐름을 제어할 수 있다.
	//코드의 흐름이 서버상에서만 이동한다.
	//브라우저는 다른 곳으로 흐름이 이동되었다는 것을 알 수 없기 떄문에 주소창의 주소는 변경되지 않는다.
	//HttpServletRequest, HttpSession 모두 유지된다.
	@GetMapping("/test2")
	public String test2() {
		return "forward:/sub2";
	}
	
	@GetMapping("/sub2")
	public String sub2() {
		return "sub2";
	}
}
