package com.woomin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.woomin.beans.DataBean1;

@Controller
//@ModelAttribute로 sessionBean1이라는 이름으로 주입받는 객체는 request영역이 아닌 session 영역에 저장한다는 의미.
@SessionAttributes("sessionBean1")
public class TestController {

	//@SessionAttributes를 통해 session영역에 저장하려면 객체를 생성해 반환하는 메서드를 반드시 작성해주어야한다.
	//SessionAttributes의 이름에 있는 sessionBean1이라는 메서드가 자동으로 호출이되고 반환하는 객체 주소값을 sessionBean1이라는 이름으로 session영역에 저장한다.
	@ModelAttribute("sessionBean1")
	public DataBean1 sessionBean1() {
		return new DataBean1();
	}
	
	//spring에서는 session객체를 바로 주입받아서 사용할 수 있다.
	@GetMapping("/test1")
	//public String test1(HttpServletRequest request) {
	public String test1(HttpSession session) {
		//HttpSession session = request.getSession();
		session.setAttribute("data1", "문자열1");
		return "test1";
	}
	
	@GetMapping("/test2")
	public String test2(HttpSession session) {
		session.setAttribute("data1", "문자열2");
		return "redirect:/result1";
	}
	
	@GetMapping("/test3")
	public String test3(HttpSession session) {
		session.setAttribute("data1", "문자열3");
		return "forward:/result1";
	}
	
	@GetMapping("/result1")
	//public String result1(HttpServletRequest request) {
	public String result1(HttpSession session) {
		//HttpSession session = request.getSession();
		String data1 = (String) session.getAttribute("data1");
		System.out.printf("data1 : %s\n", data1);
		
		return "result1";
	}
	
	@GetMapping("/test4")
	public String test4(HttpSession session) {
	//@SessionAttribute("bean1")는 이미 "bean1"이름으로 저장된 객체를 주입받는거지 새로운 객체가 주입되면서 "bean1"로 저장되는게 아니라 오류가남
	//public String test4(@SessionAttribute("bean1") DataBean1 bean1) {
		
		DataBean1 bean1 = new DataBean1();
		bean1.setData1("문자열4");
		bean1.setData2("문자열5");
		session.setAttribute("bean1", bean1);
		
		return "test4";
	}
	
	//Session에서 getAttribute로 가져올 수 있지만 매개변수로 @SessionAttribute로 바로 주입받을 수도 있다.
	@GetMapping("/result4")
	//public String result4(HttpSession session) {
	public String result4(@SessionAttribute("bean1") DataBean1 bean1) {
	
		// bean1 = (DataBean1) session.getAttribute("bean1");
		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());
		
		return "result4";
	}
	
	@GetMapping("/test5")
	public String test5(@ModelAttribute("sessionBean1") DataBean1 sessionBean1) {
		
		sessionBean1.setData1("문자열6");
		sessionBean1.setData2("문자열7");
		
		return "test5";
	}
	
	@GetMapping("/result5")
	public String result5(@ModelAttribute("sessionBean1") DataBean1 sessionBean1) {
		
		System.out.printf("sessionBean1.data1 : %s\n", sessionBean1.getData1());
		System.out.printf("sessionBean1.data2 : %s\n", sessionBean1.getData2());
		
		return "result5";
		
		
	}
}
