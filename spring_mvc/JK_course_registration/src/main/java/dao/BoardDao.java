package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.BoardBean;
import mapper.BoardMapper;

@Repository
public class BoardDao {

	@Autowired
	private BoardMapper boardMapper;
	
	//글 목록
	public List<BoardBean> getBoardList(int cr_key, RowBounds rowBounds){
		List<BoardBean> board_list = boardMapper.getBoardList(cr_key, rowBounds);
		
		return board_list;
	}
	
	//전체 페이지네이션
	public String getTotalContentCnt(int cr_key) {
		
		return boardMapper.getTotalContentCnt(cr_key);
		
	}
	
	//글작성자로 검색하여 페이지네이션
	public String getUserContentCnt(int cr_key, String user_name) {
		
		return boardMapper.getUserContentCnt(cr_key, user_name);
		
	}
	
	//제목으로 검색하여 페이지네이션
	public String getTitleContentCnt(int cr_key, String brd_title) {
		
		return boardMapper.getTitleContentCnt(cr_key, brd_title);
		
	}
	
	//게시글로 검색하여 페이지네이션
	public String getContentCnt(int cr_key, String brd_content) {
		
		return boardMapper.getContentCnt(cr_key, brd_content);
		
	}
	
	//글작성자, 제목, 내용 전체로 검색하여 페이지네이션
	public String getTotalSearchContentCnt(int cr_key, String user_name, String brd_content, String brd_title) {
		
		return boardMapper.getTotalSearchContentCnt(cr_key, user_name, brd_content, brd_title);
		
	}
	
	//글 작성
	//return 타입이 없으니까 메서드를 그대로 호출해줌.
	public void addBoard(BoardBean addBoardBean) {

			boardMapper.addBoard(addBoardBean);
	}
	
	
	//cr_course 가져오기
	public String selCourse(int cr_key) {
		String cr_course = boardMapper.selCourse(cr_key);
		
		return cr_course;
	}
	
	//글 읽기
	public BoardBean readBoard(int brd_key) {
		BoardBean readBoard = boardMapper.readBoard(brd_key);
		
		return readBoard;
	}
	
	//조회수 증가
	public void addHit(int brd_key) {
		boardMapper.addHit(brd_key);
	}
	
	//글 수정
	public void editBoard(BoardBean editBoardBean) {
		boardMapper.editBoard(editBoardBean);
	}
	
	//글 삭제
	public void delBoard(int brd_key) {
		boardMapper.delBoard(brd_key);
	}
	
	//댓글 전체 삭제(게시글 삭제 시)
	public void delAllCmt(int brd_key) {
		boardMapper.delAllCmt(brd_key);
	}
	
	//좋아요가 있을 시 글 삭제
	public void delBoardLike(int brd_key) {
		boardMapper.delBoardLike(brd_key);
	}
	
	//좋아요 증가
	public void addLike(BoardBean addLikeBoardBean) {
		boardMapper.addLike(addLikeBoardBean);
	}
	
	//좋아요 했는지 확인
	public BoardBean chkBoardLike(BoardBean chkBoardLikeBean) {
		
		BoardBean chkLikeBean = boardMapper.chkBoardLike(chkBoardLikeBean);
		
		return chkLikeBean;
	}
	
	//좋아요 취소
	public void deleteLike(int brd_key, int user_key) {
		
		boardMapper.deleteLike(brd_key, user_key);
	}
	
	//댓글 총 갯수
	public int totalComment(int brd_key) {
		
		int totalComment = boardMapper.totalComment(brd_key);
		
		return totalComment;
	}
	
	//댓글 작성
	public void addComment(BoardBean addCommentBean) {
		boardMapper.addComment(addCommentBean);
	}
	
	//댓글 등록순 조회
	public List<BoardBean> ascComment(int brd_key){
		
		List<BoardBean> commentList = boardMapper.ascComment(brd_key);
	
		return commentList;
	}
	
	//댓글 최신순 조회
	public List<BoardBean> descComment(int brd_key){
		
		List<BoardBean> commentList = boardMapper.descComment(brd_key);
	
		return commentList;
		}
	
	//댓글 수정
	public void modifyComment(BoardBean modifyCommentBean) {
		
		boardMapper.modifyComment(modifyCommentBean);
	}
	
	//댓글 삭제
	public void deleteComment(int brd_ct_key) {
		
		boardMapper.deleteComment(brd_ct_key);
	}
	
	//작성자 이름으로 검색
	public List<BoardBean> nameSearch(int cr_key, String user_name, RowBounds rowBounds){
		
		List<BoardBean> board_list = boardMapper.nameSearch(cr_key, user_name, rowBounds);
		
		return board_list;
	}
	
	//제목으로 검색
	public List<BoardBean> titleSearch(@Param("cr_key") int cr_key, @Param("brd_title") String brd_title, RowBounds rowBounds){
		
		List<BoardBean> board_list = boardMapper.titleSearch(cr_key, brd_title, rowBounds);
		
		return board_list;
	}
	
	//게시글로 검색
	public List<BoardBean> contentSearch(@Param("cr_key") int cr_key, @Param("brd_content") String brd_content, RowBounds rowBounds){
		
		List<BoardBean> board_list = boardMapper.contentSearch(cr_key, brd_content, rowBounds);
		
		return board_list;
	}
	
	//전체로 검색
	public List<BoardBean> totalSearch(@Param("cr_key") int cr_key, @Param("brd_content") String brd_content, 
			 													@Param("user_name") String user_name, @Param("brd_title") String brd_title, RowBounds rowBounds){
		
		List<BoardBean> board_list = boardMapper.totalSearch(cr_key, brd_content, user_name, brd_title, rowBounds);
		
		return board_list;
		
	}
}
