package com.woomin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.woomin.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	private UserBean loginUserBean;
	
	//interceptor를 구현할 때 자바에서는 빈의 자동주입이 안되므로 생성자를 통해 주입해준다.
	public CheckLoginInterceptor(UserBean loginUserBean){
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(loginUserBean.isUserLogin() == false) {
			String contextPath = request.getContextPath();
			//sendRedirect는 클라이언트 브라우저가 새로운 요청을 보내므로 현재페이지 기준이 아닌 웹 애플리케이션의 루트 경로를 기준으로 지정해야하기때문에 contextPath를 지정
			response.sendRedirect(contextPath + "/user/not_login");
			return false;
		}
		return true;
	}
}
