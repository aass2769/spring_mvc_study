package com.woomin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class TestController {

	@GetMapping("/test1")
	public String test1(HttpServletRequest request) {
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");
		
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		
		for(String str1 : data3) {
			System.out.println("data3 : " + str1);
		}
		return "result";
	}
	
	@PostMapping("/test2")
	public String test2(HttpServletRequest request) {
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		
		if(data3 != null) {
			for(String str1 : data3) {
				System.out.println("data3 : " + str1);
			}
		}
		return "result";
	}
	
	//HttpServletRequest를 확장한 클래스, 기능이 확장됬다고 보면된다.
	@GetMapping("/test3")
	public String test3(WebRequest request) {
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		for(String str1 : data3) {
			System.out.println("data3 : "  + str1);
		}
		
		return "result";
	}
	
	//PathVariable은 데이터가 요청 주소에 있을 경우 값을 주입 받을 수 있는 어노테이션
	//Restful API 서버 프로그래밍 시 많이 사용함 
	//자동으로 형변환이 되어 주입된다.
	@GetMapping("/test4/{data1}/{data2}/{data3}")
	public String test4(@PathVariable int data1,
						@PathVariable int data2,
						@PathVariable int data3) {
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		System.out.println("data3 : " + data3);
		
		int add = data1 + data2 + data3;
		System.out.println("add : " + add);
		
		return "result";
	}
	
	//RequestParam
	//파라미터 데이터를 직접 주입 받을 수 있다.
	//지정된 변수의 이름과 파라미터의 이름이 같을 경우 값을 주입받는다.
	//자동 형변환 처리해준다.
	@GetMapping("/test5")
	public String test5(@RequestParam int data1,
						@RequestParam int data2,
						@RequestParam int[] data3) {
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		for(int a1 : data3) {
			System.out.println("data3 : " + a1);
			
		}
		
		return "result";
	}
	
	//파라미터 이름과 변수의 이름이 다를 경우 value로 파라미터 이름을 지정한다.
	@GetMapping("/test6")
	public String test6(@RequestParam(value = "data1") int value1,
						@RequestParam(value = "data2") int value2,
						@RequestParam(value = "data3") int[] value3) {
		System.out.println("data1 : " + value1);
		System.out.println("data2 : " + value2);
		for(int a1 : value3) {
			System.out.println("data3 : " + a1);
			
		}
		
		return "result";
	}
	
	//넘어오는 데이터가 있으면 받고 없으면 null값을 받을 때 required = false를 사용함.
	//넘어오는 데이터가 없을 때 default값을 설정하고 싶을 때 defaultValue를 사용함
	@GetMapping("/test7")
	public String test7(@RequestParam int data1,
						@RequestParam (required = false) String data2,
						@RequestParam (defaultValue = "0") int data3) {
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		System.out.println("data3 : " + data3);
		
		return "result";
	}
	
	@PostMapping("/test7")
	public String test7(@RequestParam String data1,
						@RequestParam (required = false) String data2,
						@RequestParam (defaultValue = "디폴트") String data3) {
		System.out.println("data1 : " + data1);
		if(data2.equals("")) {
			System.out.println("data2 : " + data2);
		}
		
		System.out.println("data3 : " + data3);
		
		return "result";
		
	}
}
