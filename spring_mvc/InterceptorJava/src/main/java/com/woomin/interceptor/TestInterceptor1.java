package com.woomin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor1 implements HandlerInterceptor{

	//Controller의 메서드가 호출되기 전에 호출됩니다.
	//false를 반환하면 요청 처리에 대한 진행이 중단됩니다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("TestInterceptor1 - preHandle");
		//return 타입을 false로 하면 코드의 흐름을 중단한다.
		return true;
	}
	
	//Controller의 메서드가 호출되고 난 후에 호출됩니다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("TestInterceptor1 - postHandle");
	}
	
	//View 처리까지 완료되면 호출됩니다.
	//db연결 해제나 파일닫기, 로깅, 보안검사를 수행하거나 세션을 종료할떄(로그아웃 후 세션종료) 등에 보통 사용함
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("TestInterceptor1 - afterCompletion");
	}
}
