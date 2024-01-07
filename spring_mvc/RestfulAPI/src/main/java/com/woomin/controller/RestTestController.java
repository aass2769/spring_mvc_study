package com.woomin.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woomin.beans.DataBean;

//@RestController는 Restful 웹 서비스를 생성하는데 사용한다. 주로 JSON 또는 XML같은 데이터 형식을 반환하는데 사용한다.
//데이터를 반환할 때 자동으로 직렬화되어 HTTP응답으로 전송되며 크롬 스토어의 JSON Formatter 확장프로그램을 추가하면 보기편하다.
//주로 Ajax 요청에 대한 JSON 응답을 처리하거나 REST API를 구축하는 데 사용된다.
//@RestController를 사용하기위해 jackson-databind의존성을 추가한다. (단 . boot는 의존성을 자동제공해주므로 받을필요없음)
@RestController
public class RestTestController {

	@GetMapping("/test2")
	public ResponseEntity<ArrayList<DataBean>> test2() {
		
		DataBean bean1 = new DataBean("문자열1", 100, 11.11, false);
		DataBean bean2 = new DataBean("문자열2", 200, 22.22, true);
		DataBean bean3 = new DataBean("문자열3", 300, 33.33, false);
		
		ArrayList<DataBean> list = new ArrayList<DataBean>();
		list.add(bean1);
		list.add(bean2);
		list.add(bean3);
		
		//ResponseEntity는 HTTP 응답을 나타내는 객체. HTTP 상태 코드, 헤더 및 본문 데이터를 포함하는데, 주로 RESTful 웹 서비스에서 클라이언트에게 응답을 반환하는 데 사용됩니다.
		//HttpStatus.OK는 브라우저에게 정상적인 데이터라는걸 알려주기위해 세팅
		ResponseEntity<ArrayList<DataBean>> entry = new ResponseEntity<ArrayList<DataBean>>(list, HttpStatus.OK);
		
		return entry;
	}
}
