package service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.BoardBean;
import beans.CourseBean;
import dao.MainDao;

@Service
public class MainService {

	@Autowired
	private MainDao mainDao;
	
	public List<CourseBean> getSubjectsAllCategory(){
		
		List<CourseBean> SubjectsAllCategory = mainDao.getSubjectsAllCategory();
		
		return SubjectsAllCategory;
	}
	
	public List<BoardBean> getMainList(int cr_key){
		
		RowBounds rowBounds = new RowBounds(0, 5); //몇번째, 꺼낼 갯수
		
		List<BoardBean> sub_list = mainDao.getMainList(rowBounds, cr_key);
		
		return sub_list;
	}
}
