package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import beans.CourseBean;

public interface CourseMapper {
	
	//한 과정의 과목의 정보를 가져오는 쿼리. ex)IT/SW 개발의 JAVA,C언어 등 과목들에 대한 정보
	@Select("SELECT a.cr_key, a.cr_course, b.sb_category, b.sb_subject, b.sb_photo, b.ct_key "
			+ "FROM COURSE_TABLE a "
			+ "INNER JOIN subject_table b "
			+ "on a.sb_key = b.sb_key "
			+ "WHERE b.sb_category = #{sb_category}")
	List<CourseBean> getSubjectsOneCategory(int sb_category);
	
	//전체 과정의 과목의 정보를 가져오는 쿼리
	@Select("SELECT a.cr_key, b.sb_subject, b.sb_photo "
			+ "FROM course_table a "
			+ "INNER JOIN subject_table b "
			+ "on a.sb_key = b.sb_key")
	List<CourseBean> getSubjectCategory();
	
	
	//한 과목에 대한 커리큘럼 정보를 가져오는 쿼리.
	@Select("SELECT a.cr_key, a.cr_course, a.sb_key, b.sb_category, b.sb_subject, b.sb_photo, b.sb_info_photo, c.ct_key, c.ct_category, c.ct_title, c.ct_description "
			+ "FROM COURSE_TABLE a "
			+ "LEFT OUTER JOIN subject_table b "
			+ "ON a.sb_key = b.sb_key "
			+ "LEFT OUTER JOIN subject_content_table c "
			+ "ON b.ct_key = c.ct_category "
			+ "WHERE a.cr_key = #{cr_key}"
			+ "ORDER BY a.cr_key, b.sb_key, c.ct_key")
	List<CourseBean> getSubjectInfo(int cr_key);
	
	//수강신청 페이지의 전체 과목들에 대한 정보리스트를 가져오는 쿼리
	@Select("SELECT a.cr_key, a.cr_course, a.sb_key, b.sb_subject, to_char(b.sb_start_date, 'YYYY-MM-DD') as sb_start_date, "
			+ "to_char(b.sb_end_date, 'YYYY-MM-DD') as sb_end_date, b.sb_number_people, c.rg_key, c.user_key, sb_user_count "
			+ "FROM course_table a "
			+ "LEFT OUTER JOIN SUBJECT_TABLE b ON a.sb_key = b.sb_key "
			+ "LEFT OUTER JOIN REGISTRATION_TABLE c ON b.sb_key = c.sb_key "
			+ "LEFT OUTER JOIN ( "
			+ "  SELECT sb_key, COUNT(user_key) as sb_user_count "
			+ "  FROM REGISTRATION_TABLE "
			+ "  GROUP BY sb_key "
			+ ") sb_counts ON b.sb_key = sb_counts.sb_key "
			+ "ORDER BY a.cr_key")
	List<CourseBean> getRegistrationList();
	
	//수강신청 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 쿼리
	@Select("SELECT a.cr_key, a.cr_course, a.sb_key, b.sb_category, b.sb_subject, to_char(b.sb_start_date, 'YYYY-MM-DD') as sb_start_date, "
			+ "to_char(b.sb_end_date, 'YYYY-MM-DD') as sb_end_date, b.sb_number_people, c.rg_key, c.user_key, sb_user_count "
			+ "FROM course_table a "
			+ "LEFT OUTER JOIN SUBJECT_TABLE b ON a.sb_key = b.sb_key "
			+ "LEFT OUTER JOIN REGISTRATION_TABLE c ON b.sb_key = c.sb_key "
			+ "LEFT OUTER JOIN ( "
			+ "SELECT sb_key, COUNT(user_key) as sb_user_count "
			+ "FROM REGISTRATION_TABLE "
			+ "GROUP BY sb_key "
			+ ") sb_counts ON b.sb_key = sb_counts.sb_key "
			+ "WHERE a.sb_key = #{sb_key} AND b.sb_category = #{sb_category} "
			+ "ORDER BY a.cr_key")
	List<CourseBean> getRegistrationSearchList(CourseBean registrationBean);
	
	//수강신청하는 쿼리
	@Insert("INSERT INTO REGISTRATION_TABLE(rg_key, sb_key, user_key) "
			+ "VALUES (rg_seq.NEXTVAL, #{sb_key}, #{user_key})")
	void setCourseRegistration(@Param("sb_key") int sb_key, @Param("user_key") int user_key);
	
	//수강신청 중복신청 확인하는 쿼리
	@Select("SELECT c.user_id "
			+ "FROM REGISTRATION_TABLE a "
			+ "LEFT OUTER JOIN subject_table b "
			+ "ON a.sb_key = b.sb_key "
			+ "LEFT OUTER JOIN user_table c "
			+ "ON a.user_key = c.user_key "
			+ "WHERE a.sb_key = #{sb_key} AND user_id = #{user_id} "
			+ "ORDER BY rg_key")
	String getCheckCourseId(@Param("sb_key") int sb_key, @Param("user_id") String user_id);
	
	
	//수강신청조회 페이지의 신청했던 과목들에 대한 정보리스트를 가져오는 쿼리
	@Select("SELECT a.cr_key, a.cr_course, a.sb_key, b.sb_subject, to_char(b.sb_start_date, 'YYYY-MM-DD') as sb_start_date, "
			+ "to_char(b.sb_end_date, 'YYYY-MM-DD') as sb_end_date, b.sb_number_people, c.rg_key, c.user_key, sb_user_count "
			+ "FROM course_table a "
			+ "LEFT OUTER JOIN SUBJECT_TABLE b ON a.sb_key = b.sb_key "
			+ "LEFT OUTER JOIN REGISTRATION_TABLE c ON b.sb_key = c.sb_key "
			+ "LEFT OUTER JOIN ( "
			+ "  SELECT sb_key, COUNT(user_key) as sb_user_count "
			+ "  FROM REGISTRATION_TABLE "
			+ "  GROUP BY sb_key "
			+ ") sb_counts ON b.sb_key = sb_counts.sb_key "
			+ "WHERE c.user_key = #{user_key} "
			+ "ORDER BY a.cr_key")
	List<CourseBean> getRegistrationCheckList(int user_key);
	
	//수강신청조회 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 쿼리
	@Select("SELECT a.cr_key, a.cr_course, a.sb_key, b.sb_subject, to_char(b.sb_start_date, 'YYYY-MM-DD') as sb_start_date, "
			+ "to_char(b.sb_end_date, 'YYYY-MM-DD') as sb_end_date, b.sb_number_people, c.rg_key, c.user_key, sb_user_count "
			+ "FROM course_table a "
			+ "LEFT OUTER JOIN SUBJECT_TABLE b ON a.sb_key = b.sb_key "
			+ "LEFT OUTER JOIN REGISTRATION_TABLE c ON b.sb_key = c.sb_key "
			+ "LEFT OUTER JOIN ( "
			+ "SELECT sb_key, COUNT(user_key) as sb_user_count "
			+ "FROM REGISTRATION_TABLE "
			+ "GROUP BY sb_key "
			+ ") sb_counts ON b.sb_key = sb_counts.sb_key "
			+ "WHERE c.user_key = #{user_key} and a.sb_key = #{sb_key} AND b.sb_category = #{sb_category}"
			+ "ORDER BY a.cr_key")
	List<CourseBean> getRegistrationCheckSearchList(CourseBean registrationBean);
	
	//수강 삭제하는 쿼리
	@Delete("DELETE FROM registration_table "
			+ "WHERE rg_key = #{rg_key}")
	void setRegistrationDelete(int rg_key);
	
	//검색 select태그에 사용할 sb_category와 course이름을 가져오는 쿼리
	@Select("SELECT a.cr_course, b.sb_category, count(*) "
			+ "FROM course_table a "
			+ "LEFT OUTER JOIN SUBJECT_TABLE b "
			+ "ON a.sb_key = b.sb_key "
			+ "GROUP BY a.cr_course, b.sb_category "
			+ "ORDER BY sb_category")
	List<CourseBean> getCourseList();
	
	//검색 select태그에 사용할 sb_key, sb_subject를 가져오는 쿼리
	@Select("SELECT sb_key, sb_subject "
			+ "FROM subject_table "
			+ "WHERE sb_category = #{sb_category}")
	List<CourseBean> getSubjectList(int sb_category);
	
	
}
