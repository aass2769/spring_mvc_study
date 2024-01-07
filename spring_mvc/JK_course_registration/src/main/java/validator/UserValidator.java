package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import beans.UserBean;

//Validator는 javax가 아니라 springframework로 임포트받기.
public class UserValidator implements Validator{

	//supports 메서드는 이 유효성 검사기가 어떤 클래스를 지원할지를 결정함. 예제에서는 UserBean
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	//validate 메서드는 실제 유효성 검사 로직이 들어가는 곳.이 메서드에서는 다양한 검사를 수행할 수 있다.
	//유효성 검사를 하기위한 bean객체가 target으로 넘어옴
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) target;
		
		//Errors 객체에 바인딩된 객체(데이터 바인딩 또는 유효성 검사 대상)의 이름을 반환
		String beanName = errors.getObjectName();
		
		//폼 유효성 검사에 사용된 객체의 이름이 joinUserBean일 경우 아래 유효성검사를 한다.
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				//user_pw의 오류를 NotEquals로 한다.
				errors.rejectValue("user_pw", "NotEquals");
				//user_pw의 오류를 NotEquals로 한다.
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			//회원가입 아이디 중복검사 유효성검사
			if(beanName.equals("joinUserBean")) {
				if(userBean.isDuplicate_id() == false) {
				
				errors.rejectValue("user_id", "NotCheckDuplicate");
				}
			}
		}
	}

}
