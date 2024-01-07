package com.woomin.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.woomin.beans.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	//유효성 검사를 하기위한 bean객체가 target으로 넘어옴
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean)target;
		
		//폼 유효성 검사에 사용되는 객체의 이름을 반환함.
		String beanName = errors.getObjectName();

		//폼 유효성 검사에 사용된 객체의 이름이 joinUserBean거나 modifyUserBean일경우 아래 유효성검사를 한다.
		//회원가입과 정보수정의 비밀번호 확인
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				//user_pw의 오류를 NotEquals로 한다.
				errors.rejectValue("user_pw", "NotEquals");
				//user_pw2의 오류를 NotEquals로 한다.
				errors.rejectValue("user_pw2", "NotEquals");
			}
		
		//회원가입 시 아이디 중복확인
		if(beanName.equals("joinUserBean")) {
			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
		}
	}
}
