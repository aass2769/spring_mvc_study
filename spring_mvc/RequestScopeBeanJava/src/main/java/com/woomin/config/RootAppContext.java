package com.woomin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import com.woomin.beans.DataBean1;
import com.woomin.beans.DataBean2;

// 프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class RootAppContext {

	@Bean
	//RequestScope 어노테이션을 설정하면 빈이 주입되는 시기가 새로운 요청이 발생했을 때 주입됨.
	@RequestScope
	public DataBean1 dataBean1() {
		return new DataBean1();
	}
	
	@Bean("requestBean2")
	@RequestScope
	public DataBean2 dataBean2() {
		return new DataBean2();
	}
}
