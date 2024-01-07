package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.CourseBean;
import mapper.CourseMapper;

@Repository
public class CourseDao {

	@Autowired
	private CourseMapper courseMapper;
	
	//한 과정의 과목의 정보를 가져오는 쿼리. ex)IT/SW 개발의 JAVA,C언어 등 과목들에 대한 정보
	public List<CourseBean> getSubjectsOneCategory(int sb_category){
		
		List<CourseBean> subjectsList = courseMapper.getSubjectsOneCategory(sb_category);
		
		return subjectsList;
	}
	
	public List<CourseBean> getSubjectCategory(){
		
		List<CourseBean> subjectsList = courseMapper.getSubjectCategory();
		
		return subjectsList;
	}
	
	//한 과목에 대한 커리큘럼 정보를 가져오는 메서드
	public List<CourseBean> getSubjectInfo(int cr_key){
		
		List<CourseBean> subjectInfoList = courseMapper.getSubjectInfo(cr_key);
		
		return subjectInfoList;
	}
	
	//수강신청 페이지의 전체 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationList(){
		
		List<CourseBean> registrationList = courseMapper.getRegistrationList();
		
		return registrationList;
	}
	
	//수강신청 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationSearchList(CourseBean registrationBean){
		
		List<CourseBean> registrationList = courseMapper.getRegistrationSearchList(registrationBean);
		
		return registrationList;
	}
	
	//수강신청 하는 메서드
	public void setCourseRegistration(int sb_key, int user_key) {
		
		courseMapper.setCourseRegistration(sb_key, user_key);
	}
	
	//수강신청 중복신청 확인하는 메서드
	public String getCheckCourseId(int sb_key, String user_id) {
		
		String checkCourseId = courseMapper.getCheckCourseId(sb_key, user_id);
		
		return checkCourseId;
	}
	
	//수강신청조회 페이지의 신청했던 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationCheckList(int user_key){
		
		List<CourseBean> registrationCheckList = courseMapper.getRegistrationCheckList(user_key);
		
		return registrationCheckList;
	}
	
	//수강신청조회 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationCheckSearchList(CourseBean registrationBean){
		
		List<CourseBean> registrationCheckList = courseMapper.getRegistrationCheckSearchList(registrationBean);
		
		return registrationCheckList;
	}
	
	//수강 삭제하는 메서드
	public void setRegistrationDelete(int rg_key) {
		
		courseMapper.setRegistrationDelete(rg_key);
	}
	
	//검색 select태그에 사용할 sb_category와 course이름을 가져오는 메서드
	public List<CourseBean> getCourseList(){
		
		List<CourseBean> courseList = courseMapper.getCourseList();
		
		return courseList;
	}
	
	//검색 select태그에 사용할 sb_key, sb_subject를 가져오는 메서드
	public List<CourseBean> getSubjectList(int sb_category){
		
		List<CourseBean> subjectList = courseMapper.getSubjectList(sb_category);
		
		return subjectList;
	}
}
