package dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.BoardBean;
import beans.CourseBean;
import mapper.MainMapper;

@Repository
public class MainDao {

	@Autowired
	private MainMapper mainMapper;
	
	
	public List<CourseBean> getSubjectsAllCategory(){
		
		List<CourseBean> SubjectsAllCategory = mainMapper.getSubjectsAllCategory();
		
		return SubjectsAllCategory;
	}
	
	public List<BoardBean> getMainList(RowBounds rowBounds, int cr_key){
			
			List<BoardBean> sub_list = mainMapper.getMainList(rowBounds, cr_key);
			
			return sub_list;
		}
}
