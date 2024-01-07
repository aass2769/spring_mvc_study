package com.woomin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.woomin.beans.DataBean;

@Controller
public class TestController {
 
	//넘어오는 데이터 이름을보고 bean안에 있는 동일한 이름의 프로퍼티에 set해주게 된다.
	//ModelAttribute로 주입받으면 그 bean은 request영역에 자동으로 담기게 된다. 앞글자는 빈의 앞글자 소문자로 ex)dataBean
	//@ModelAttribute는 생략이 가능하다.
	@PostMapping("/test1")
	//public String test1(@ModelAttribute DataBean bean) {
	public String test1(DataBean bean) {
		
		//System.out.printf("data1 : %s\n", bean.getData1());
		//System.out.printf("data2 : %s\n", bean.getData2());
		
		return "test1";
	}
	
	//클래스의 이름을 정의해줄떄는 ModelAttribute를 정의해주어야한다.
	//클래스의 이름을 정의하면 그 이름으로 담기고 정의하지않으면 클래스이름의 앞글자는 소문자로 request영역에 담김
	@PostMapping("/test2")
	public String test2(@ModelAttribute("testData") DataBean bean) {
		return "test2";
	}
}
