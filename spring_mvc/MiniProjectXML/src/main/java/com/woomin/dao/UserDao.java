package com.woomin.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woomin.beans.UserBean;

@Repository
public class UserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public String checkUserIdExist(String user_id) {
		//하나를 리턴할땐 selectOne, 리스트일땐 selectList
		//mapper의 namespace.select문 id
		return sqlSessionTemplate.selectOne("user.checkUserIdExist", user_id);
	}
	
	public void addUserInfo(UserBean joinUserBean) {
		sqlSessionTemplate.insert("user.addUserInfo", joinUserBean);
	}
	
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		return sqlSessionTemplate.selectOne("user.getLoginUserInfo", tempLoginUserBean);
	}
	
	public UserBean getModifyUserInfo(int user_idx) {
		return sqlSessionTemplate.selectOne("user.getModifyUserInfo", user_idx);
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		sqlSessionTemplate.update("user.modifyUserInfo", modifyUserBean);
	}
}
