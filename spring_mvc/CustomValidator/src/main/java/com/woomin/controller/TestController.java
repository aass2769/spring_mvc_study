package com.woomin.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.woomin.beans.DataBean1;
import com.woomin.validator.DataBean1Validator;

@Controller
public class TestController {

	@GetMapping("/input_data")
	public String input_data(DataBean1 dataBean1) {
		
		return "input_data";
	}
	
	@PostMapping("/input_pro")
	public String input_pro(@Valid DataBean1 dataBean1, BindingResult result) {
		
		if(result.hasErrors()) {
			return "input_data";
		}
		
		return "input_success";
	}
	
	//@InitBinder 어노테이션은 컨트롤러 내에서 데이터 바인딩 및 유효성 검사를 커스터마이징할 떄 사용됨.
	//WebDataBinder 객체를 인자로 받아서 데이터 바인딩 및 검증을 구성함.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//DataBean1Validator라는 유효성 검사기(Validator)를 생성하고, 이를 WebDataBinder에 추가합니다.
		//이렇게 하면 해당 컨트롤러에서 사용하는 데이터 모델(DataBean1 객체)의 유효성 검사가 DataBean1Validator를 통해 수행된다.
		DataBean1Validator validator1 = new DataBean1Validator();
		// binder.setValidator(validator1);
		binder.addValidators(validator1);
	}
}












