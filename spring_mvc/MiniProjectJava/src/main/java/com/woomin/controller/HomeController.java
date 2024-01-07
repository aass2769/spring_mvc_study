package com.woomin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	
public class HomeController {

//	@Resource(name = "loginUserBean")
//	private UserBean loginUserBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		//System.out.println(loginUserBean);
		
		//서버에서 파일의 위치는 실제 위치지만 이클립스에서는 프로젝트를 복사해서 다른 폴더에서 실행시키기 떄문에 그 경로를 찾아서 파일위치를 설정해야함.
		//주소만 쳤을때 실제 파일의 위치가 어디인지 절대경로를 보여줌
		//System.out.println(request.getServletContext().getRealPath("/"));
		
		return "redirect:/main";
	}
}
