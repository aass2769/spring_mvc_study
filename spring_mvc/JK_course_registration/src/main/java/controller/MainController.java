package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import beans.BoardBean;
import beans.CourseBean;
import service.CourseService;
import service.MainService;
import service.TopService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TopService topService;
	
	@GetMapping("/main")
	public String main(Model model) {
		
		List<CourseBean> allCategoryList = mainService.getSubjectsAllCategory();
		model.addAttribute("allCategoryList", allCategoryList);
		
		List<CourseBean> courseList = courseService.getCourseList();
		
		model.addAttribute("courseList", courseList);
		
		/*게시판 자체 총 갯수 3개 가져오는 리스트*/
		ArrayList<List<BoardBean>> sub_list = new ArrayList<>();
		for(int i=1; i<10; i=i+4) { //게시판의 cr_key가 각각 1, 5, 9
			List<BoardBean> sub_list_in = mainService.getMainList(i);
			sub_list.add(sub_list_in);
		}
		model.addAttribute("sub_list", sub_list);
		
		List<CourseBean> subjectsList = courseService.getSubjectCategory();
		model.addAttribute("subjectsList", subjectsList);
		
		List<CourseBean> duplicateList = topService.courseList();
		model.addAttribute("duplicateList", duplicateList);
		
		return "main";
	}
	
}
