package service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.CourseBean;
import beans.UserBean;
import dao.CourseDao;

@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;
	
	//로그인 sessionScope 빈
	@Resource(name = "userSession")
	private UserBean userSession;
	
	//한 과정의 과목의 정보를 가져오는 쿼리. ex)IT/SW 개발의 JAVA,C언어 등 과목들에 대한 정보
	public List<CourseBean> getSubjectsOneCategory(int sb_category){
		
		List<CourseBean> subjectsList = courseDao.getSubjectsOneCategory(sb_category);
		
		return subjectsList;
	}
	
	public List<CourseBean> getSubjectCategory(){
		
		List<CourseBean> subjectsList = courseDao.getSubjectCategory();
		
		return subjectsList;
	}
	
	//한 과목에 대한 커리큘럼 정보를 가져오는 메서드
	public List<CourseBean> getSubjectInfo(int cr_key){
		
		List<CourseBean> subjectInfoList = courseDao.getSubjectInfo(cr_key);
		
		return subjectInfoList;
	}
	
	//수강신청 페이지의 전체 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationList(){
		
		List<CourseBean> registrationList = courseDao.getRegistrationList();
		List<CourseBean> duplicateCheckRgList = new ArrayList<CourseBean>();
		
		for(int i=0; i<registrationList.size(); i++) {
			
			boolean isDuplicate = false;
			
			for(int j=0; j<duplicateCheckRgList.size(); j++) {
				
				CourseBean duplicateRgBean = duplicateCheckRgList.get(j);
				
				if(duplicateRgBean.getCr_key() == registrationList.get(i).getCr_key()) {
					isDuplicate = true;
					break; //중복
				}
			}
			
			//중복이 아니라면
			if(!isDuplicate) {
				CourseBean registrationBean = new CourseBean();
				registrationBean.setCr_key(registrationList.get(i).getCr_key());
				registrationBean.setCr_course(registrationList.get(i).getCr_course());
				registrationBean.setSb_key(registrationList.get(i).getSb_key());
				registrationBean.setSb_subject(registrationList.get(i).getSb_subject());
				registrationBean.setSb_start_date(registrationList.get(i).getSb_start_date());
				registrationBean.setSb_end_date(registrationList.get(i).getSb_end_date());
				registrationBean.setSb_number_people(registrationList.get(i).getSb_number_people());
				registrationBean.setRg_key(registrationList.get(i).getRg_key());
				registrationBean.setSb_user_count(registrationList.get(i).getSb_user_count());
				duplicateCheckRgList.add(registrationBean);
			}
		}
	
		return duplicateCheckRgList;
	}
	
	//수강신청 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationSearchList(CourseBean registrationBean){
		
		List<CourseBean> registrationList = courseDao.getRegistrationSearchList(registrationBean);
		List<CourseBean> duplicateCheckRgList = new ArrayList<CourseBean>();
		
		for(int i=0; i<registrationList.size(); i++) {
			
			boolean isDuplicate = false;
			
			for(int j=0; j<duplicateCheckRgList.size(); j++) {
				
				CourseBean duplicateRgBean = duplicateCheckRgList.get(j);
				
				if(duplicateRgBean.getCr_key() == registrationList.get(i).getCr_key()) {
					isDuplicate = true;
					break; //중복
				}
			}
			
			//중복이 아니라면
			if(!isDuplicate) {
				registrationBean.setCr_key(registrationList.get(i).getCr_key());
				registrationBean.setCr_course(registrationList.get(i).getCr_course());
				registrationBean.setSb_key(registrationList.get(i).getSb_key());
				registrationBean.setSb_subject(registrationList.get(i).getSb_subject());
				registrationBean.setSb_start_date(registrationList.get(i).getSb_start_date());
				registrationBean.setSb_end_date(registrationList.get(i).getSb_end_date());
				registrationBean.setSb_number_people(registrationList.get(i).getSb_number_people());
				registrationBean.setRg_key(registrationList.get(i).getRg_key());
				registrationBean.setSb_user_count(registrationList.get(i).getSb_user_count());
				duplicateCheckRgList.add(registrationBean);
			}
		}
	
		return duplicateCheckRgList;
	}
	
	//수강신청과 조회 페이지에서 과정종류선택 
	
	
	//수강신청 하는 메서드
	public void setCourseRegistration(int sb_key) {
		
		int user_key = userSession.getUser_key();
		
		courseDao.setCourseRegistration(sb_key, user_key);
	}
	
	//수강신청 중복신청 확인하는 메서드
	public boolean getCheckCourseId(int sb_key) {
		
		String user_id = userSession.getUser_id();
		
		String checkCourseId = courseDao.getCheckCourseId(sb_key, user_id);
		//true면 수강신청 가능
		boolean checkCourse;
		
		//null이 아니라는것은 Id가 있다는 의미이므로 수강신청 불가능
		if(checkCourseId != null) {
			checkCourse = false;
		}else {
			checkCourse = true;
		}
		
		return checkCourse;
	}
	
	//수강신청조회 페이지의 신청했던 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationCheckList(){
		
		int user_key = userSession.getUser_key();
		List<CourseBean> registrationCheckList = courseDao.getRegistrationCheckList(user_key);
		
		return registrationCheckList;
	}
	
	//수강신청조회 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 메서드
	public List<CourseBean> getRegistrationCheckSearchList(CourseBean registrationBean){
		
		List<CourseBean> registrationCheckList = courseDao.getRegistrationCheckSearchList(registrationBean);
		
		return registrationCheckList;
	}
	
	//수강 삭제하는 메서드
	public void setRegistrationDelete(int rg_key) {
		courseDao.setRegistrationDelete(rg_key);
	}
	
	//검색 select태그에 사용할 sb_category와 course이름을 가져오는 메서드
	public List<CourseBean> getCourseList(){
		
		List<CourseBean> courseList = courseDao.getCourseList();
		
		return courseList;
	}
	
	//검색 select태그에 사용할 sb_key, sb_subject를 가져오는 메서드
	public List<CourseBean> getSubjectList(int sb_category){
		
		List<CourseBean> subjectList = courseDao.getSubjectList(sb_category);
		
		return subjectList;
	}
	
}
