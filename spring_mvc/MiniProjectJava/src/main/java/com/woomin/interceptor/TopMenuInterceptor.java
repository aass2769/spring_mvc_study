package com.woomin.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.woomin.beans.BoardInfoBean;
import com.woomin.beans.UserBean;
import com.woomin.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{

	//interceptor는 스프링 빈으로 관리되지 않기때문에 @Autowired를 사용할 수 없다.
	private TopMenuService topMenuService;
	
	private UserBean loginUserBean;
	
	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
	
}
