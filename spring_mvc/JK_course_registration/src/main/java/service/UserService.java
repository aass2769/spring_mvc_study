package service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.UserBean;
import dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//로그인 sessionScope 빈
	@Resource(name = "userSession")
	private UserBean userSession;
	
	//회원가입
	public void setUserSignUp(UserBean joinUserBean) {
		userDao.setUserSignUp(joinUserBean);
	}
	
	//중복확인을 위해 유저아이디를 가져오는 메서드
	public boolean checkDuplicateId(String user_id) {
		
		String duplicate_id = userDao.checkDuplicateId(user_id);
		
		//가져온 값이 null이면 db에 user_id값이 없다는 말이므로 true 리턴
		if(duplicate_id == null) {
			return true;
		} else {
			return false;
		}
	}
	
	//로그인 메서드
	public UserBean userLoginIn(UserBean loginUserBean) {
		
		UserBean userCheck = userDao.userLoginIn(loginUserBean);
		
		if(userCheck != null) {
			userSession.setUser_key(userCheck.getUser_key());
			userSession.setUser_id(userCheck.getUser_id());
			userSession.setUser_name(userCheck.getUser_name());
			userSession.setUserLogin(true);
		}
		
		return userCheck;
	}
	
	//정보수정 유저 정보가져오기 메서드
	public void getModifyUserInfo(UserBean modifyUserBean) {
		
		UserBean tempModifyUserBean = userDao.getModifyUserInfo(userSession.getUser_key());
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
	}
	
	//정보수정하는 메서드
	public void setModifyUserInfo(UserBean modifyUserBean) {
		modifyUserBean.setUser_key(userSession.getUser_key());
		userDao.setModifyUserInfo(modifyUserBean);
	}
	
	//비밀번호찾기 메서드
	public String getPassword(UserBean forgetUserBean) {
		String password = userDao.getPassword(forgetUserBean);
		return password;
	}
}
