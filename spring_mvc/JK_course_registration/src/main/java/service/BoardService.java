package service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import beans.BoardBean;
import beans.BoardPageBean;
import beans.UserBean;
import dao.BoardDao;

@Service
//파일업로드 경로있는 프로퍼티 파일 로드.
@PropertySource("/WEB-INF/properties/fileUploadOption.properties")
public class BoardService {

	@Value("${path.upload}") //@value를 이용하여 프로퍼티 파일의 path.upload를
	private String path_upload; //path_upload라는 문자열 변수에 주입.
	
	//페이지에 보여줄 게시글의 수
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	//페이지네이션 버튼의 수
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "userSession")
	private UserBean userSession;
	
	//게시물 목록 리스트 코드
	public List<BoardBean> getBoardList(int cr_key, int page){
		
		//page_listcnt
		int start = (page - 1) * page_listcnt;
		//1페이지 0~9개 2페이지 10~19개
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		//몇번째부터, 총 몇개
		
		List<BoardBean> board_list = boardDao.getBoardList(cr_key, rowBounds);
		
		return board_list;
	}
	
	//전체 페이지네이션
	public BoardPageBean getTotalContentCnt(int cr_key, int currentPage) {
		
		//전체 글 갯수
		String cnt = boardDao.getTotalContentCnt(cr_key);
		if(cnt != null) {
			int content_cnt = Integer.parseInt(cnt);
			BoardPageBean boardPageBean =  new BoardPageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
			return boardPageBean;
		}else {
			BoardPageBean boardPageBean =  new BoardPageBean();
			boardPageBean.setPageCnt(0);
			return boardPageBean;
		}
	}
		
	//글작성자로 검색하여 페이지네이션
	public BoardPageBean getUserContentCnt(int cr_key, int currentPage, String user_name) {
		
		//전체 글 갯수
		String cnt = boardDao.getUserContentCnt(cr_key, user_name);
		if(cnt != null) {
			int content_cnt = Integer.parseInt(cnt);
			BoardPageBean boardPageBean =  new BoardPageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
			return boardPageBean;
		} else {
			BoardPageBean boardPageBean =  new BoardPageBean();
			boardPageBean.setPageCnt(0);
			return boardPageBean;
		}
	}
	
	//제목으로 검색하여 페이지네이션
	public BoardPageBean getTitleContentCnt(int cr_key, int currentPage, String brd_title) {
		
		//전체 글 갯수
		String cnt = boardDao.getTitleContentCnt(cr_key, brd_title);
		if(cnt != null) {
			int content_cnt = Integer.parseInt(cnt);
			BoardPageBean boardPageBean =  new BoardPageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
			return boardPageBean;
		} else {
			BoardPageBean boardPageBean =  new BoardPageBean();
			boardPageBean.setPageCnt(0);
			return boardPageBean;
		}
	}
	
	//게시글로 검색하여 페이지네이션
	public BoardPageBean getContentCnt(int cr_key, int currentPage, String brd_content) {
		
		//전체 글 갯수
		String cnt = boardDao.getContentCnt(cr_key, brd_content);
		if(cnt != null) {
			int content_cnt = Integer.parseInt(cnt);
			BoardPageBean boardPageBean =  new BoardPageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
			return boardPageBean;
		} else {
			BoardPageBean boardPageBean =  new BoardPageBean();
			boardPageBean.setPageCnt(0);
			return boardPageBean;
		}
	}
	
	//글작성자, 제목, 내용 전체로 검색하여 페이지네이션
	public BoardPageBean getTotalSearchContentCnt(int cr_key, int currentPage, String user_name, String brd_content, String brd_title) {
		
		//전체 글 갯수
		String cnt = boardDao.getTotalSearchContentCnt(cr_key, user_name, brd_content, brd_title);
		if(cnt != null) {
			int content_cnt = Integer.parseInt(cnt);
			BoardPageBean boardPageBean =  new BoardPageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
			return boardPageBean;
		} else {
			BoardPageBean boardPageBean =  new BoardPageBean();
			boardPageBean.setPageCnt(0);
			return boardPageBean;
		}
	}
	
	
	//게시물 작성 코드
	public void addBoard(BoardBean addBoardBean) {
		//user_key를 session에서 받아와서 빈에 set함.
		addBoardBean.setUser_key(userSession.getUser_key());
		//Brd_writer에 user_key를 넣어서, 로그인한 글쓴이와 글작성자 인덱스가 일치하게 함.
		addBoardBean.setBrd_writer(userSession.getUser_key());
		
		//파일업로드
		MultipartFile upload_file = addBoardBean.getUpload_File();
		
		if(upload_file.getSize() > 0) {
			//파일업로드 한거 저장하는 메서드인 saveUploadFile메서드를 호출함.
			String file_name = saveUploadFile(upload_file);
			addBoardBean.setBrd_file(file_name);
		}
		
		boardDao.addBoard(addBoardBean);
	}
	
	//cr_course 가져오기
	public String selCourse(int cr_key) {
		String cr_course = boardDao.selCourse(cr_key);
		
		return cr_course;
	}
	
	
	//게시물 읽기 코드
	public BoardBean readBoard(int brd_key) {
		
		BoardBean readBoard = boardDao.readBoard(brd_key);
		
		return readBoard;
	}
	
	//게시글 조회수 증가 코드
	public void addHit(int brd_key) {
		boardDao.addHit(brd_key);
	}
	
	//게시글 수정 코드
	public void editBoard(BoardBean editBoardBean) {
		
		//파일업로드
		MultipartFile upload_file = editBoardBean.getUpload_File();
		
		if(upload_file.getSize() > 0) {
			//파일업로드 한거 저장하는 메서드인 saveUploadFile메서드를 호출함.
			String file_name = saveUploadFile(upload_file);
			editBoardBean.setBrd_file(file_name);
		}
		boardDao.editBoard(editBoardBean);
	}
	
	//게시글 삭제 코드
	public void delBoard(int brd_key) {
		boardDao.delBoard(brd_key);
	}
	
	//댓글 전체 삭제(게시글 삭제 시)
	public void delAllCmt(int brd_key) {
		boardDao.delAllCmt(brd_key);
	}
	
	//좋아요가 있을 시, 게시글 삭제 코드
	public void delBoardLike(int brd_key) {
		boardDao.delBoardLike(brd_key);
	}
	
	//파일업로드한거 저장하는 코드
	//(클라이언트에서 전송된 파일을 서버에 업로드하고, 새로운 파일 이름을 생성하여 반환하는 역할)
	private String saveUploadFile(MultipartFile upload_file) {
		
		//System.currentTimeMillis() : 현재 시간을 나타내는 메서드로, 파일 이름의 중복을 피하고자 사용됨.
		//~.getOriginalFilename() : 클라이언트가 업로드한 파일의 원래 이름을 가져옴.
		//==그래서 최종적으로, 두 값을 조합하여 새로운 파일 이름을 생성함.
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			//~.transferTo : 업로드 된 파일 데이터를 지정된 경로에 저장함.
			//프로퍼티 파일에서 읽어온, 업로드 된 파일을 저장할 디렉토리 경로(path_upload)에 파일이 저장됨.
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return file_name; //업로드 된 파일의 새로운 이름인 file_name을 반환.
	}
	
	//좋아요 증가
	public void addLike(BoardBean addLikeBoardBean) {
		
		addLikeBoardBean.setUser_key(userSession.getUser_key());
		
		boardDao.addLike(addLikeBoardBean);
		
	}
	
	//좋아요 했는지 확인하는 쿼리
	public boolean chkBoardLike(int brd_key) {
			
			BoardBean chkBoardLikeBean = new BoardBean();
			chkBoardLikeBean.setBrd_key(brd_key);
			chkBoardLikeBean.setUser_key(userSession.getUser_key());
		
			BoardBean chkLikeBean = boardDao.chkBoardLike(chkBoardLikeBean);
			
			boolean chkLike = false;
			//값이 있어서 가져와진다면, 좋아요를 했다는 뜻
			if(chkLikeBean != null) {
				
				chkLike = true;
				
			}else{
				chkLike = false;
			}
			
			return chkLike;
		}
	
	//좋아요 취소하는 쿼리
	public void deleteLike(int brd_key) {
		
		int user_key = userSession.getUser_key();
		
		boardDao.deleteLike(brd_key, user_key);
	}
	
	//댓글 총 갯수
	public int totalComment(int brd_key) {
		
		int totalComment = boardDao.totalComment(brd_key);
		
		return totalComment;
	}

	//댓글 작성
	public void addComment(BoardBean addCommentBean) {
		boardDao.addComment(addCommentBean);
	}
	
	//댓글 등록순 조회
	public List<BoardBean> ascComment(int brd_key){
		
		List<BoardBean> commentList = boardDao.ascComment(brd_key);
	
		return commentList;
	}
	
	//댓글 최신순 조회
	public List<BoardBean> descComment(int brd_key){
		
		List<BoardBean> commentList = boardDao.descComment(brd_key);
	
		return commentList;
		}
	//댓글 수정
	public void modifyComment(BoardBean modifyCommentBean) {
		
		boardDao.modifyComment(modifyCommentBean);
	}
	
	//댓글 삭제
	public void deleteComment(int brd_ct_key) {
		
		boardDao.deleteComment(brd_ct_key);
		
	}
	
	//작성자 이름으로 검색
	public List<BoardBean> nameSearch(int cr_key, String user_name, int page){
		
		//page_listcnt = 10
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		//몇번째부터, 총 몇개
		
		List<BoardBean> board_list = boardDao.nameSearch(cr_key, user_name, rowBounds);
		
		return board_list;
	}
	
	//제목으로 검색
	public List<BoardBean> titleSearch(@Param("cr_key") int cr_key, @Param("brd_title") String brd_title, int page){
		
		//page_listcnt = 10
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		//몇번째부터, 총 몇개
				
		List<BoardBean> board_list = boardDao.titleSearch(cr_key, brd_title, rowBounds);
		
		return board_list;
	}
	
	//게시글로 검색
	public List<BoardBean> contentSearch(@Param("cr_key") int cr_key, @Param("brd_content") String brd_content, int page){
		
		//page_listcnt = 10
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		//몇번째부터, 총 몇개
		
		List<BoardBean> board_list = boardDao.contentSearch(cr_key, brd_content, rowBounds);
		
		return board_list;
	}
	
	//전체로 검색
	public List<BoardBean> totalSearch(@Param("cr_key") int cr_key, @Param("brd_content") String brd_content, 
			 													@Param("user_name") String user_name, @Param("brd_title") String brd_title, int page){
		
		//page_listcnt = 10
			int start = (page - 1) * page_listcnt;
			RowBounds rowBounds = new RowBounds(start, page_listcnt);
			//몇번째부터, 총 몇개
		
		List<BoardBean> board_list = boardDao.totalSearch(cr_key, brd_content, user_name, brd_title, rowBounds);
		
		return board_list;
		
	}
}
