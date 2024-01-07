package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import beans.CourseBean;
import service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/subjects")
	public String subjects(int sb_category, Model model) {
		
		//한 과정의 과목들에 대한 정보를 가져오는 메서드
		List<CourseBean> subjectsList = courseService.getSubjectsOneCategory(sb_category);
		model.addAttribute("subjectsList", subjectsList);
		
		return "course/subjects";
	}
	
	@GetMapping("/subject_view")
	public String subject_view(int cr_key, Model model) {
		
		//한 과목의 정보를 가져오는 메서드
		List<CourseBean> subjectInfoList = courseService.getSubjectInfo(cr_key);
		model.addAttribute("subjectInfoList", subjectInfoList);
		
		return "course/subject_view";
	}
	
	@GetMapping("/registration")
	public String registration(@ModelAttribute("registrationBean") CourseBean registrationBean, Model model) {
		
		//검색 select태그에 사용할 sb_category와 course이름을 가져오는 메서드
		List<CourseBean> courseList = courseService.getCourseList();
		model.addAttribute("courseList", courseList);
		

		
		if(registrationBean.getSb_category() == 0 && registrationBean.getSb_key() == 0) {
			
			//수강신청 페이지의 전체 과목들에 대한 정보리스트를 가져오는 메서드
			List<CourseBean> duplicateCheckRgList = courseService.getRegistrationList();
			model.addAttribute("duplicateCheckRgList", duplicateCheckRgList);
			
			//수강신청 과목 개수
			int subjectCount = duplicateCheckRgList.size();
			model.addAttribute("subjectCount", subjectCount);
			
		} else {
			
			//수강신청 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 메서드
			List<CourseBean> duplicateCheckRgList = courseService.getRegistrationSearchList(registrationBean);
			model.addAttribute("duplicateCheckRgList", duplicateCheckRgList);
			
			//게시글 개수
			int subjectCount = duplicateCheckRgList.size();
			model.addAttribute("subjectCount", subjectCount);
		}

		return "course/registration";
	}
	
	@PostMapping("/registration_pro")
	public String registration_pro(@ModelAttribute("registrationBean") CourseBean registrationBean, @RequestParam int sb_key,
									@RequestParam(value="over", defaultValue ="true") boolean over) {
		
		//수강신청을 했던 항목인지 체크하는 메서드
		boolean checkCourse = courseService.getCheckCourseId(sb_key);
		if(checkCourse == false) {
			
			return "course/registration_fail";
		}
		
		if(over == false) {
			return "course/registration_over";
		}
		//수강신청 하는 메서드
		courseService.setCourseRegistration(sb_key);
		
		return "course/registration_success";
	}
	
	
	@GetMapping("/registration_check")
	public String registration_check(@ModelAttribute("registrationBean") CourseBean registrationBean, Model model) {
		
		//검색 select태그에 사용할 sb_category와 course이름을 가져오는 메서드
		List<CourseBean> courseList = courseService.getCourseList();
		model.addAttribute("courseList", courseList);
		
		if(registrationBean.getSb_category() == 0 && registrationBean.getSb_key() == 0) {
			//수강신청조회 페이지의 신청했던 과목들에 대한 정보리스트를 가져오는 메서드
			List<CourseBean> registrationCheckList = courseService.getRegistrationCheckList();
			model.addAttribute("registrationCheckList", registrationCheckList);
			
			//수강신청한 과목 개수
			int subjectCount = registrationCheckList.size();
			model.addAttribute("subjectCount", subjectCount);
		} else {
			//수강신청 페이지의 검색한 과목들에 대한 정보리스트를 가져오는 메서드
			List<CourseBean> registrationCheckList = courseService.getRegistrationCheckSearchList(registrationBean);
			model.addAttribute("registrationCheckList", registrationCheckList);
			
			//게시글 개수
			int subjectCount = registrationCheckList.size();
			model.addAttribute("subjectCount", subjectCount);
		}
		
		
		
		return "course/registration_check";
	}
	
	@PostMapping("/registrationDelete_pro")
	public String registrationDelete_pro(int rg_key) {
		
		//수강 삭제하는 메서드
		courseService.setRegistrationDelete(rg_key);
		
		return "course/registration_delete";
	}
}
