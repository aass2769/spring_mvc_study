package com.woomin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//프로그램 실행 중 변하지 않는 값들을 정의해서 사용할 떄 properties로 설정하면 유용하며 아래의 3가지 방법으로 사용할 수 있다.
//@PropertySource("/WEB-INF/properties/data1.properties")
//@PropertySource("/WEB-INF/properties/data2.properties")

//@PropertySource(value = {
//		"/WEB-INF/properties/data1.properties",
//		"/WEB-INF/properties/data2.properties"
//})

@PropertySources({@PropertySource("/WEB-INF/properties/data1.properties"),
				  @PropertySource("/WEB-INF/properties/data2.properties")})
public class TestController {
	
	@Value("${aaa.a1}")
	private int a1;
	
	@Value("${aaa.a2}")
	private String a2;
	
	@Value("${bbb.b1}")
	private int b1;
	
	@Value("${bbb.b2}")
	private String b2;
	
	@Value("${ccc.c1}")
	private int c1;
	
	@Value("${ccc.c2}")
	private String c2;
	
	@Value("${ddd.d1}")
	private int d1;
	
	@Value("${ddd.d2}")
	private String d2;

	@GetMapping("/test1")
	public String test1() {
		
		System.out.printf("aaa.a1 : %d\n", a1);
		System.out.printf("aaa.a2 : %s\n", a2);
		
		System.out.printf("bbb.b1 : %d\n", b1);
		System.out.printf("bbb.b2 : %s\n", b2);
		
		System.out.printf("ccc.c1 : %d\n", c1);
		System.out.printf("ccc.c2 : %s\n", c2);
		
		System.out.printf("ddd.d1 : %d\n", d1);
		System.out.printf("ddd.d2 : %s\n", d2);
		return "test1";
	}
}
