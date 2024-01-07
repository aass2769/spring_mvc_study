package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.CourseBean;
import mapper.TopMapper;

@Repository
public class TopDao {

	@Autowired
	private TopMapper topMapper;
	
	public List<CourseBean> courseList(){
		
		List<CourseBean> course_list = topMapper.courseList();
		
		return course_list;
	}
}
