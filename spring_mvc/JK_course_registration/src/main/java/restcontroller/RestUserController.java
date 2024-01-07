package restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import service.UserService;

@RestController
public class RestUserController {

	@Autowired
	private UserService userService;
	
	//@PathVarialbe은 RESTful 웹 애플리케이션에서 URL 경로의 일부를 매개변수로 가져오는 데 사용.
	//요청의 경로에서 동적인 값을 추출하고 사용함.
	@GetMapping("/user/checkDuplicateId/{user_id}")
	public String checkDuplicateId(@PathVariable String user_id) {
		
		boolean duplicate_id = userService.checkDuplicateId(user_id);
		
		//boolean의 return 값을 문자열로 변환하기 위해 +""를 붙임.
		return duplicate_id + "";
	}
}
