package com.woomin.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.woomin.beans.DataBean1;

@Controller
public class TestController {

	@GetMapping("/input_data")
	public String input_data() {
		
		return "input_data";
	}
	
	@PostMapping("/input_pro")
	//매개변수에 modelAttribute가 생략되고 request영역에 자동으로 저장됨
	//Bean에 @Valid를 설정하면 유효성검사를 실시함. 결과를 사용하고자 하면 BindingResult객체를 주입받아야함.
	public String input_pro(@Valid DataBean1 dataBean1, BindingResult result) {
		
		System.out.printf("data1 : %s\n", dataBean1.getData1());
		System.out.printf("data2 : %d\n", dataBean1.getData2());
		
		System.out.printf("BindingResult : %s\n", result);
		
		//유효성 검사에서 위반된 부분이 있다면
		if(result.hasErrors()) {
			//유효성 위반 결과들 모두 가져온다.
			for(ObjectError obj : result.getAllErrors()) {
				System.out.printf("메시지 : %s\n", obj.getDefaultMessage());
				System.out.printf("code : %s\n", obj.getCode());
				System.out.printf("ObjectName : %s\n", obj.getObjectName());
				
				String[] codes = obj.getCodes();
				
				//String 배열로 getCodes를 받으면 배열로 오류를 받는데 0번쨰 인덱스값이 Max.dataBean1.data2 이런식으루 나옴
//				for(String c1 : codes) {
//					System.out.println(c1);
//				}
				
//				System.out.println(codes[0]);
				
				if(codes[0].equals("Size.dataBean1.data1")) {
					//이부분엔 실제로 유효성 위반에 대한 코드처리가 들어감
					System.out.println("data1은 2~10글자를 담을 수 있습니다.");
				} else if(codes[0].equals("Max.dataBean1.data2")) {
					//이부분엔 실제로 유효성 위반에 대한 코드처리가 들어감
					System.out.println("data2는 100이하의 값만 담을 수 있습니다.");
				}
				
				
				System.out.println("------------------------------");
			}
			return "input_data";
		}
		
		return "input_success";
	}
}
