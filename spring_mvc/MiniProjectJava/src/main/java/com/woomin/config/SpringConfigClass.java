package com.woomin.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//public class SpringConfigClass implements WebApplicationInitializer{
//
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//
//		//Spring MVC 프로젝트 설정을 위해 작성하는 클래스의 객체를 생성한다.
//		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
//		servletAppContext.register(ServletAppContext.class);
//		
//		//요청 발생 시 요청을 처리하는 서블릿을 DispatcherServlet으로 설정해준다.
//		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
//		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
//		
//		//부가 설정
//		servlet.setLoadOnStartup(1);
//		servlet.addMapping("/");
//		
//		//Bean을 정의하는 클래스를 지정한다.
//		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
//		rootAppContext.register(RootAppContext.class);
//		
//		//리스너 설정
//		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
//		servletContext.addListener(listener);
//		
//		// 파라미터 인코딩 설정
//		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//		filter.setInitParameter("encoding", "UTF-8");
//		filter.addMappingForServletNames(null, false, "dispatcher");
//	}
//
//}

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer{
	// DistpatcherServlet에 매핑할 요청 주소를 셋팅한다.
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	// Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletAppContext.class};
	}
	
	// 프로젝트에서 사용할 Bean들을 정의 하기 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootAppContext.class};
	}
	
	// 파라미터 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {

		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}
	
	//멀티파트 파일 업로드 구성을 설정하고, 파일 업로드에 대한 다양한 설정을 지정한다.
	//MultipartConfigElement를 사용하여 멀티파트 파일 업로드 설정을 커스터마이즈하기 위해 오버라이드된 메서드
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		super.customizeRegistration(registration);
		
		//MultipartConfigElement로 멀티파트 파일 업로드 설정을 지정함.
		//첫번째 매개변수 - 파일 업로드 시 임시로 생성되는 파일을 저장할 디렉토리의 경로를 지정. 서블릿 컨테이너(예: Apache Tomcat)에서 관리하며, 임시 파일은 여기에 저장됨.null로 설정 시 자체 임시 디렉토리 사용.
		//두번째 매개변수 - 업로드된 파일 하나의 최대 허용 크기.byte단위. 여기선 50byte.
		//세번째 매개변수 - 멀티파트 요청 자체의 최대 허용 크기.byte단위.
		//네번째 매개변수 - 멀티파트 요청을 처리하는 동안 임시파일로 저장하기 전에 버퍼 메모리에 유지해야하는 파일의 최소크기.byte단위. 0은 모든 파일은 디스크에 즉시 저장.
		MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
		registration.setMultipartConfig(config1);
	}
}


