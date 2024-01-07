package com.woomin.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.woomin.beans.DataBean1;

//DataBean1Validator 클래스는 Spring의 Validator 인터페이스를 구현한 클래스로, 
//특정 모델 클래스(DataBean1)에 대한 유효성 검사 규칙을 정의
public class DataBean1Validator implements Validator{

	//supports 메서드는 이 유효성 검사기가 어떤 클래스를 지원할지를 결정함. 예제에서는 DataBean1
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DataBean1.class.isAssignableFrom(clazz);
	}
	
	//validate 메서드는 실제 유효성 검사 로직이 들어가는 곳.이 메서드에서는 다양한 검사를 수행할 수 있습니다.
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		//값이 있거나 공백이 있으면 통과 값이 없으면 오류
		ValidationUtils.rejectIfEmpty(errors, "data2", "error2");
		//값이 있어야 통과 공백이나 값이 없으면 오류
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data3", "error3");
		
		DataBean1 bean1 = (DataBean1)target;
		
		String data2 = bean1.getData2();
		String data3 = bean1.getData3();
		
		if(data2.length() > 10) {
			//rejectValue는 특정 필드에 대한 유효성 검사 오류를 추가하는데 사용
			errors.rejectValue("data2", "error4");
		}
		
		if(data3.contains("@") == false) {
			errors.rejectValue("data3", "error5");
		}
	}

}







