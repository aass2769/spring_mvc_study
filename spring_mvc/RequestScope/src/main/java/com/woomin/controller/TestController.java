package com.woomin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.woomin.beans.DataBean1;

@Controller
public class TestController {

	@GetMapping("/test1")
	public String test1(HttpServletRequest request) {
		
		request.setAttribute("data1", "문자열1");
		
		return "forward:/result1";
	}
	
	@GetMapping("/result1")
	public String result1(HttpServletRequest request) {
		
		String data1 = (String) request.getAttribute("data1");
		System.out.printf("data1 : %s\n", data1);
		return "result1";
	}
	
	@GetMapping("/test2")
	public String test2(Model model) {
		
		model.addAttribute("data2", "문자열2");
		
		return "forward:/result2";
	}
	
	//위 test2메서드에서 모델을 통해서 데이터를 저장하면 request영역에 저장이 되기때문에 아래 result에서 Model을 주입받으면안되구
	//request를 주입받아야한다.
//	@GetMapping("/result2")
//	public String result2(Model model) {
//		
//		String data2 = (String) model.getAttribute("data2");
//		System.out.printf("data2 : %s\n", data2);
//		
//		return "result2";
//	}
	
	@GetMapping("/result2")
	public String result2(HttpServletRequest request) {
		
		String data2 = (String) request.getAttribute("data2");
		System.out.printf("data2 : %s\n", data2);
		
		return "result2";
	}
	
	//ModelAndView도 마찬가지로 ModelAndView에 저장하면 request영역에 저장되기 때문에 아래 result3에서
	//HttpServletRequest로 받아야한다.
	@GetMapping("/test3")
	public ModelAndView test3(ModelAndView mv) {
		
		mv.addObject("data3", "문자열3");
		mv.setViewName("forward:/result3");
		
		return mv;
	}
	
	@GetMapping("/result3")
	public String result3(HttpServletRequest request) {
		String data3 = (String) request.getAttribute("data3");
		System.out.printf("data3 : %s\n", data3);
		
		return "result3";
	}
	
	//model에 저장했기 때문에 request영역에 저장이됨.
	@GetMapping("/test4")
	public String test4(Model model) {
		DataBean1 bean1 = new DataBean1();
		bean1.setData1("문자열4");
		bean1.setData2("문자열5");
		
		model.addAttribute("bean1", bean1);
		
		return "forward:/result4";
	}
	
	@GetMapping("result4")
	public String result4(HttpServletRequest request) {
		
		DataBean1 bean1 = (DataBean1) request.getAttribute("bean1");
		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());
		
		return "result4";
	}
	
	//DataBean1 클래스의 bean1에 저장을 했지만 request영역에 저장이됨.
	@GetMapping("/test5")
	public String test5(@ModelAttribute("bean1") DataBean1 bean1) {
		bean1.setData1("문자열6");
		bean1.setData2("문자열7");
		
		return "forward:/result5";
	}
	
	//DataBean1 bean1을 주입받으면 새로운 bean1객체가 생성되며 주입받는거기 때문에 set했던 문자열6과 7을 사용할 수 없음
	@GetMapping("/result5")
	//public String result5(@ModelAttribute("bean1") DataBean1 bean1) {
	public String result5(HttpServletRequest request) {
		DataBean1 bean1 = (DataBean1) request.getAttribute("bean1");
		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());
		
		return "result5";
	}
	
	//url로 받은 파라미터를 forward하기
	@GetMapping("/test6")
	public String test6(@RequestParam("data1") String data1, @RequestParam("data2") String data2, Model model) {
		
		model.addAttribute("data1", data1);
		model.addAttribute("data2", data2);
		return "forward:/result6";
	}
	
	@GetMapping("/result6")
	public String result6(HttpServletRequest request) {
		String data1 = (String) request.getAttribute("data1");
		String data2 = (String) request.getAttribute("data2");
		System.out.printf("data1 : %s\n", data1);
		System.out.printf("data2 : %s\n", data2);
		return "result6";
	}
	
}
