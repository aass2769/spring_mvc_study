package service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.CourseBean;
import beans.UserBean;
import dao.TopDao;

@Service
public class TopService {

   @Autowired
   private TopDao topDao;
   
   public List<CourseBean> courseList(){
      
      List<CourseBean> course_list = topDao.courseList();
      
      List<CourseBean> check_list = new ArrayList<CourseBean>();
      
      for (int i = 0; i < course_list.size(); i++) {
           int check_cr_key = course_list.get(i).getCr_key();
           String check_cr_course = course_list.get(i).getCr_course();
           int sb_category = course_list.get(i).getSb_category();

           // 중복 검사용 플래그 변수
           boolean isDuplicate = false;

           for (int j = 0; j < check_list.size(); j++) {
               CourseBean check_bean = check_list.get(j);

               if (check_bean.getCr_key() != check_cr_key && check_bean.getCr_course().equals(check_cr_course)) {
                   isDuplicate = true;
                   break; // 중복 발견, 같지않으면 중복 /같으면 중복아님
               }
           }

           if (!isDuplicate) {
        	   
               // 중복이 없을 때만 check_list에 추가
               CourseBean newCheckBean = new CourseBean();
               newCheckBean.setCr_key(check_cr_key);
               newCheckBean.setCr_course(check_cr_course);
               newCheckBean.setSb_category(sb_category);
               check_list.add(newCheckBean);
           }
       }
      
      return check_list;
   }
}