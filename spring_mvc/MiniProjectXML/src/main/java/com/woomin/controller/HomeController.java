package com.woomin.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.woomin.beans.UserBean;

@Controller	
public class HomeController {

//	@Resource(name = "loginUserBean")
//	//java에서는 최초의 요청이 발생했을 때 주입이되지만 xml은 서버가 가동할 때 주입이된다.
//	//해당 빈은 sessionScope기 때문에 오류가 발생하여 lazy 어노테이션을 해준다.
//	@Lazy
//	private UserBean loginUserBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
//		System.out.println(loginUserBean);
		return "redirect:/main";
	}
}
