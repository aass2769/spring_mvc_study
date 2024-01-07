package com.woomin.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	//쿠키는 문자열 데이터만 보내는게 가능
	@GetMapping("/save_cookie")
	public String save_cookie(HttpServletResponse response) {
		
		try {
			//쿠키의 한글이 꺠질 수 있기떄문에 인코딩해서 보내기
			String data1 = URLEncoder.encode("문자열1", "UTF-8");
			String data2 = URLEncoder.encode("문자열2", "UTF-8");
			
			Cookie cookie1 = new Cookie("cookie1", data1);
			Cookie cookie2 = new Cookie("cookie2", data2);
			
			cookie1.setMaxAge(365*24*60*60);
			cookie1.setMaxAge(365*24*60*60);
			
			//응답결과로 쿠키를 담아서 전달
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "save_cookie";
	}
	
	@GetMapping("/load_cookie")
	public String load_cookie(HttpServletRequest request) {
		
		//servlet,jsp에서는 쿠키를 가저오는데 원하는 쿠키를 한번에 꺼낼 수 있는게아니라 모든쿠키를 가져오며 반복문을
		//돌려서 원하는 쿠키를 꺼내야한다.
		try {
			
			Cookie[] cookies = request.getCookies();
			
			for(Cookie cookie : cookies) {
				String str = URLDecoder.decode(cookie.getValue(), "UTF-8");
				
				if(cookie.getName().equals("cookie1")) {
					System.out.printf("cookie1 : %s\n", str);
				} else if(cookie.getName().equals("cookie2")) {
					System.out.printf("cookie2 : %s\n", str);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "load_cookie";
	}
	
	//spring에서 제공하는 어노테이션을 사용하면 원하는 쿠키를 바로 받을 수 있고 인코딩 디코딩을 하지 않아도 되는 장점이 있다.
	@GetMapping("/load_cookie2")
	public String load_cookie2(@CookieValue("cookie1") String cookie1,
								@CookieValue("cookie2") String cookie2,
								Model model) {
		
		System.out.printf("cookie1 : %s\n", cookie1);
		System.out.printf("cookie2 : %s\n", cookie2);
		
		model.addAttribute("cooki1", cookie1);
		model.addAttribute("cookie2", cookie2);
		
		return "load_cookie2";
	}
}
