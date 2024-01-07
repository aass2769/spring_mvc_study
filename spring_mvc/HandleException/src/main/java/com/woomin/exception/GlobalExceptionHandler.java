package com.woomin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//컨트롤러가 여러개라면 컨트롤러마다 @ExceptionHandler를 구현하면 비효율적이기 떄문에 GlobalException을 구현
//RuntimeException을 상속받아야함
@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

	//메서드 이름 자유롭게
	@ExceptionHandler(NullPointerException.class)
	public String NullPointerException() {
		
		return "error2";
	}
}
