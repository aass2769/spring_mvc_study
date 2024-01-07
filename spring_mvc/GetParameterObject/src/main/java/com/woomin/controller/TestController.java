package com.woomin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.woomin.beans.DataBean;
import com.woomin.beans.DataBean2;

@Controller
public class TestController {

	//클라이언트가 전달 하는 모든 파라미터 데이터를 한번에 Map으로 받을 수 있다.
	//동일 명으로 전달되는 2개 이상의 파라미터는 하나만 담긴다.
	//동일 명으로 전달되는 파라미터가 2개 이상이라면 List로 주입 받아야한다.
	//형변환이 자동으로 되지 않기 떄문에 무조건 String으로 받아야한다. Integer로 받으면 오류 발생.
	@GetMapping("/test1")
	public String test1(@RequestParam Map<String, String> map, @RequestParam List<String> data3) {
		String data1 = map.get("data1");
		String data2 = map.get("data2");
		String data33 = map.get("data3");
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		System.out.println("data33 : " + data33);
		
		for(String str1 : data3) {
			System.out.println("data3 : " + str1);
		}
		return "result"; 
	}
	
	//ModelAttribute 어노테이션을 사용하면 파라미터를 객체로 주입받을 수 있다.
	//전달되는 파라미터의 이름과 동일한 프로퍼티(필드)에 자동으로 주입된다.
	//자동으로 형변환이 된다.
	//ModelAttribute는 생략이 가능하다.
	//이러한 객체를 커맨드 객체라고 한다.
	@GetMapping("/test2")
	//public String test2(@ModelAttribute DataBean bean1, @ModelAttribute DataBean2 bean2) {
	public String test2(DataBean bean1, DataBean2 bean2) {
		System.out.println("data1 : " + bean1.getData1());
		System.out.println("data2 : " + bean1.getData2());
				
		for(int number1 : bean1.getData3()) {
			System.out.println("data3 : " + number1);
		}
		
		System.out.println("bean2.data1 : " + bean2.getData1());
		System.out.println("bean2.data2 : " + bean2.getData2());

		
		return "result";
	}
}
