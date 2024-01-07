package restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import beans.CourseBean;
import service.CourseService;

@RestController
public class RestCourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/course/subjectList/{sb_category}")
	public List<CourseBean> subjectList(@PathVariable int sb_category) {
		
		//검색 select태그에 사용할 sb_key, sb_subject를 가져오는 메서드
		List<CourseBean> subjectList = courseService.getSubjectList(sb_category);
		
		return subjectList;
	}
	
}
