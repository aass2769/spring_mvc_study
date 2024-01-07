package interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import beans.CourseBean;
import beans.UserBean;
import service.TopService;

public class TopInterceptor implements HandlerInterceptor {

	//로그인 sessionScope 빈
	private UserBean userSession;
	
	private TopService topService;
	
	public TopInterceptor(UserBean userSession, TopService topService) {
		this.userSession = userSession;
		this.topService = topService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		List<CourseBean> course_list = topService.courseList();
		
		request.setAttribute("course_list", course_list);
		request.setAttribute("userSession", userSession);
		
		return true;
 		
	}
}
