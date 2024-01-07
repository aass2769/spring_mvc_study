package com.woomin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woomin.dao.TestDAO;

@Service
public class TestService {

	@Autowired
	private TestDAO testDao;
	
	
	public String testMethod() {
		
		String str = testDao.testDaoMethod();
		
		return str;
	}
}
