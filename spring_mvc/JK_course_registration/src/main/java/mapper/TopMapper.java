package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import beans.CourseBean;

public interface TopMapper {

	@Select("SELECT a.cr_key, a.cr_course, b.sb_category "
			+ "FROM COURSE_TABLE a "
			+ "INNER JOIN subject_table b "
			+ "on a.sb_key = b.sb_key")
	List<CourseBean> courseList();
}
	