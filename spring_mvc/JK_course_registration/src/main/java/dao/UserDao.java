package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.UserBean;
import mapper.UserMapper;

@Repository
public class UserDao {

	@Autowired
	private UserMapper userMapper;
	
	//회원가입
	public void setUserSignUp(UserBean joinUserBean) {
		userMapper.setUserSignUp(joinUserBean);
	}
	
	//중복확인을 위해 유저아이디를 가져오는 메서드
	public String checkDuplicateId(String user_id) {
		return userMapper.checkDuplicateId(user_id);
	}
	
	//로그인 메서드
	public UserBean userLoginIn(UserBean loginUserBean) {
		return userMapper.userLoginIn(loginUserBean);
	}
	
	//정보수정 유저 정보가져오기 메서드
	public UserBean getModifyUserInfo(int user_key) {
		return userMapper.getModifyUserInfo(user_key);
	}
	
	//정보수정하는 메서드
	public void setModifyUserInfo(UserBean modifyUserBean) {
		userMapper.setModifyUserInfo(modifyUserBean);
	}
	
	//비밀번호찾기 메서드
	public String getPassword(UserBean forgetUserBean) {
		String password = userMapper.getPassword(forgetUserBean);
		return password;
	}
}
