package com.woomin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.woomin.beans.ContentBean;

public interface BoardMapper {

	//@SelectKey는 고유 식별자를 생성하기 위해 사용.
	//statement의 쿼리의 결과값이 keyProperty로 세팅되는데 다른 쿼리가 실행되기 전에 실행.
	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class)
	
	//게시글 insert메서드
	//db에서 null을 허용으로 설정해놨어도 mybatis에서는 허용하지 않기때문에 jdbcType=VARCHAR같이 타입을 명시적으로 작성해줌
	@Insert("insert into content_table(content_idx, content_subject, content_text, " +
			"content_file, content_writer_idx, content_board_idx, content_date)" +
			"values (#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
			"#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
	
	//게시판 이름(ex:자유게시판)select
	@Select("select board_info_name " + 
			"from board_info_table " +
			"where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	@Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, " +
			"    to_char(a1.content_date, 'YYYY-MM-DD') as content_date " +
			"from content_table a1, user_table a2 " +
			"where a1.content_writer_idx = a2.user_idx " +
			"    and a1.content_board_idx = #{board_info_idx} " +
			"order by a1.content_idx desc")
	List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds);
	
	@Select("select a2.user_name as content_writer_name, " +
			"        to_char(a1.content_date, 'YYYY-MM-DD') as content_date, " +
			"        a1.content_subject, a1.content_text, a1.content_file, a1.content_writer_idx " +
			"from content_table a1, user_table a2 " +
			"where a1.content_writer_idx = a2.user_idx "+
			"    and content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx);
	
	@Update("update content_table " +
			"set content_subject = #{content_subject}, content_text = #{content_text}, " + 
			"content_file = #{content_file, jdbcType=VARCHAR} " +
			"where content_idx = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);
	
	@Delete("delete from content_table where content_idx = #{content_idx}")
	void deleteContentInfo(int content_idx);
	
	@Select("select count(*) from content_table where content_board_idx = #{content_board_idx}")
	int getContentCnt(int content_board_idx);
}
