package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.UserBean;

public interface UserMapper {

	//회원가입 쿼리
	@Insert("INSERT INTO user_table(user_key, user_id, user_pw, user_name) "
			+ "VALUES (user_seq.NEXTVAL, #{user_id}, #{user_pw}, #{user_name})")
	void setUserSignUp(UserBean joinUserBean);
	
	//중복확인을 위해 유저아이디를 가져오는 메서드
	@Select("SELECT user_id "
			+ "FROM user_table "
			+ "WHERE user_id = #{user_id}")
	String checkDuplicateId(String user_id);
	
	//로그인 메서드
	@Select("SELECT user_key, user_id, user_name "
			+ "FROM user_table "
			+ "WHERE user_id = #{user_id} AND user_pw = #{user_pw}")
	UserBean userLoginIn(UserBean loginUserBean);
	
	//정보수정 유저 정보가져오는 메서드
	@Select("SELECT user_key, user_id, user_name "
			+ "FROM user_table "
			+ "WHERE user_key = #{user_key}")
	UserBean getModifyUserInfo(int user_key);
	
	//정보수정하는 메서드
	@Update("UPDATE user_table "
			+ "SET user_pw = #{user_pw} "
			+ "WHERE user_key = #{user_key} AND user_id = #{user_id}")
	void setModifyUserInfo(UserBean modifyUserBean);
	
	//비밀번호찾기 메서드
	@Select("SELECT USER_PW FROM user_table "
			+ "WHERE USER_ID = #{user_id} AND USER_NAME = #{user_name}")
	String getPassword(UserBean forgetUserBean);
	
}
