package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import beans.BoardBean;
import beans.BoardPageBean;
import beans.CourseBean;
import beans.UserBean;
import service.BoardService;
import service.TopService;

@Controller
@RequestMapping("/board")
public class BoardController {

	//로그인 sessionScope 빈
	@Resource(name = "userSession")
	private UserBean userSession;
	
	@Autowired
	private TopService topService;
	
	@Autowired
	private BoardService boardService;
	
	/*게시글 목록 페이지*/
	@GetMapping("/detail")
	public String detail(@RequestParam("cr_key") int cr_key, @RequestParam("cr_course") String cr_course,
									@ModelAttribute("searchBean") BoardBean searchBean, Model model,
									@RequestParam(value = "page", defaultValue = "1") int page) {
		
		//게시판 카테고리 식별을 위한 키
		model.addAttribute("cr_key", cr_key);
		model.addAttribute("cr_course", cr_course);
		
		if(searchBean.getBrd_search_category() == null) {
		/*게시글 전체 목록 불러오기*/
		List<BoardBean> board_list = boardService.getBoardList(cr_key, page);
		model.addAttribute("board_list", board_list);
		BoardPageBean boardPageBean = boardService.getTotalContentCnt(cr_key, page);
		
		model.addAttribute("boardPageBean", boardPageBean);
		model.addAttribute("total", "전체");
		model.addAttribute("page", page);
		
		
		}
		
		else if(searchBean.getBrd_search_category().equals("글작성자")) {
			String user_name = searchBean.getBrd_search_content();
			
			/*게시글 이름 검색*/
			List<BoardBean> board_list_name = boardService.nameSearch(cr_key, user_name, page);
			model.addAttribute("board_list", board_list_name);
			BoardPageBean boardPageBean = boardService.getUserContentCnt(cr_key, page, user_name);
			if(boardPageBean.getPageCnt() == 0) {
				return "board/fail_search";
			}
			model.addAttribute("boardPageBean", boardPageBean);
			model.addAttribute("user_name", user_name);
			model.addAttribute("page", page);
		}
		
		else if(searchBean.getBrd_search_category().equals("제목")) {
			String brd_title = searchBean.getBrd_search_content();
			
			/*게시글 제목 검색*/
			List<BoardBean> board_list_title = boardService.titleSearch(cr_key, brd_title, page);
			model.addAttribute("board_list", board_list_title);
			BoardPageBean boardPageBean = boardService.getTitleContentCnt(cr_key, page, brd_title);
			if(boardPageBean.getPageCnt() == 0) {
				return "board/fail_search";
			}
			model.addAttribute("boardPageBean", boardPageBean);
			model.addAttribute("brd_title", brd_title);
			model.addAttribute("page", page);
			
		}

		else if(searchBean.getBrd_search_category().equals("게시글")) {
			String brd_content = searchBean.getBrd_search_content();
			
			/*게시글의 내용 검색*/
			List<BoardBean> board_list_content = boardService.contentSearch(cr_key, brd_content, page);
			model.addAttribute("board_list", board_list_content);
			BoardPageBean boardPageBean = boardService.getContentCnt(cr_key, page, brd_content);
			if(boardPageBean.getPageCnt() == 0) {
				return "board/fail_search";
			}
			model.addAttribute("boardPageBean", boardPageBean);
			model.addAttribute("brd_content", brd_content);
			model.addAttribute("page", page);
			
		}
		
		else if(searchBean.getBrd_search_category().equals("전체")) {
			String brd_content = searchBean.getBrd_search_content();
			String user_name = searchBean.getBrd_search_content();
			String brd_title = searchBean.getBrd_search_content();
			
			/*게시글 제목 검색*/
			List<BoardBean> board_list_total = boardService.totalSearch(cr_key, brd_content, user_name, brd_title, page);
			model.addAttribute("board_list", board_list_total);
			BoardPageBean boardPageBean = boardService.getTotalSearchContentCnt(cr_key, page, user_name, brd_content, brd_title);
			if(boardPageBean.getPageCnt() == 0) {
				return "board/fail_search";
			}
			model.addAttribute("boardPageBean", boardPageBean);
			model.addAttribute("user_name", user_name);
			model.addAttribute("brd_content", brd_content);
			model.addAttribute("brd_title", brd_title);
			model.addAttribute("page", page);
			
		}
		
		return "board/detail";
	}
	
	/*게시글 작성 페이지*/
	@GetMapping("/create")
	public String create(Model model, @ModelAttribute("addBoardBean") BoardBean addBoardBean) {
		
		/*select 선택에서 가져오기 위한 list*/
		List<CourseBean> course_list = topService.courseList();
		
		model.addAttribute("course_list", course_list);

		return "board/create";
	}
	
	/*게시글 작성하는 과정 처리하는 코드*/
	/*BindingResult는 바인딩의 대상이 되는 객체 바로 뒤에 위치해야 한다.*/
	@PostMapping("/create_pro")
	public String create_pro(@Valid @ModelAttribute("addBoardBean") BoardBean addBoardBean, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "board/create";
		}
		
		//cr_course 가져오기
		String cr_course = boardService.selCourse(addBoardBean.getCr_key());
		model.addAttribute("cr_course", cr_course);

		//게시글 인서트해주는 메서드
		boardService.addBoard(addBoardBean);
		
		return "board/create_done";
	}
	
	
	/*게시글 읽기 페이지*/
	@GetMapping("/read")
	public String read(@RequestParam("brd_key") int brd_key, @ModelAttribute("addCommentBean") BoardBean addCommentBean,
									@RequestParam(value="sort", defaultValue = "등록순") String sort, Model model) {
	
		//조회수 증가 메서드
		boardService.addHit(brd_key);
		
		//글 읽기 메서드
		BoardBean readBoard = boardService.readBoard(brd_key);
		model.addAttribute("readBoard", readBoard);
		
		//좋아요를 로그인한 사용자가 했는지 확인하는 메서드
		boolean chkLike = boardService.chkBoardLike(brd_key);
		model.addAttribute("chkLike", chkLike);
		
		int user_key = userSession.getUser_key();
		model.addAttribute("user_key", user_key);
		
		//댓글 조회 - 등록, 최신메서드
		if(sort.equals("등록순")) {
			List<BoardBean> commentList = boardService.ascComment(brd_key);
			model.addAttribute("commentList", commentList);
			model.addAttribute("sort",sort);
		} else if(sort.equals("최신순")) {
			List<BoardBean> commentList = boardService.descComment(brd_key);
			model.addAttribute("commentList", commentList);
			model.addAttribute("sort",sort);
		}
		
		//댓글 총 갯수
		int totalComment = boardService.totalComment(brd_key);
		model.addAttribute("totalComment", totalComment);
		
		return "board/read";
	}
	
	//게시물 수정 메서드
	@GetMapping("/modify")
	public String modify (@RequestParam("brd_key") int brd_key, @ModelAttribute("editBoardBean") BoardBean editBoardBean, Model model) {
		
		//글 읽기 메서드
		BoardBean readBoard = boardService.readBoard(brd_key);
		
		editBoardBean.setBrd_key(readBoard.getBrd_key());
		editBoardBean.setUser_name(readBoard.getUser_name());
		editBoardBean.setBrd_date(readBoard.getBrd_date());
		editBoardBean.setBrd_title(readBoard.getBrd_title());
		editBoardBean.setBrd_content(readBoard.getBrd_content());
		editBoardBean.setBrd_file(readBoard.getBrd_file());
		
		//글 수정 메서드
		
		return "board/modify";
	}

	//게시글 수정하는 과정 메서드
	@PostMapping("modify_pro")
	public String modify_pro(@ModelAttribute("editBoardBean") BoardBean editBoardBean, Model model) {
		
			boardService.editBoard(editBoardBean);
		
		return "board/modify_done";
	}

	//게시글 삭제하는 메서드
	@GetMapping("/delete")
	public String delete(@RequestParam("brd_key") int brd_key, @RequestParam("cr_key") int cr_key, 
						 @RequestParam("cr_course") String cr_course, Model model) {
		
		//좋아요 있을 시, 좋아요 먼저 삭제
		boardService.delBoardLike(brd_key);

		//댓글 있을 시, 댓글 먼저 삭제
		boardService.delAllCmt(brd_key);
		
		//좋아요 없을 시, 게시글 삭제
		boardService.delBoard(brd_key);
		
		model.addAttribute("cr_key", cr_key);
		model.addAttribute("cr_course", cr_course);
		
		return "board/delete_done";
	}
	
	//좋아요 증가
	@PostMapping("/addLike")
	public String addLike(@ModelAttribute("addLikeBoardBean") BoardBean addLikeBoardBean) {
		
		boardService.addLike(addLikeBoardBean);
		
		return "board/like_done";
	}
	
	//좋아요 취소
	@PostMapping("/deleteLike")
	public String deleteLike(int brd_key, Model model) {
		
		boardService.deleteLike(brd_key);
		//jsp로 보내기 위해 model에 담는다.
		model.addAttribute("brd_key", brd_key);
		
		return "board/like_delete_done";
	}
	
	//댓글 작성
	@PostMapping("/addComment")
	public String addComment(@ModelAttribute("addCommentBean") BoardBean addCommentBean, 
			@RequestParam("brd_key") int brd_key, @RequestParam("user_key") int user_key, @RequestParam("cr_key") int cr_key,
			@RequestParam("cr_course") String cr_course, Model model) {
		
		boardService.addComment(addCommentBean);
		
		model.addAttribute("brd_key", brd_key);
		model.addAttribute("user_key", user_key);
		model.addAttribute("cr_key", cr_key);
		model.addAttribute("cr_course", cr_course);
		
		return "board/add_ct_done";
	}
	
	
	//댓글 삭제
	@GetMapping("/deleteComment")
	public String deleteComment(@RequestParam("cr_key") int cr_key, @RequestParam("cr_course") String cr_course, 
													@RequestParam("brd_ct_key") int brd_ct_key, @RequestParam("brd_key") int brd_key,
													@RequestParam("user_key") int user_key, Model model) {
		
		boardService.deleteComment(brd_ct_key);
		
		model.addAttribute("cr_key", cr_key);
		model.addAttribute("cr_course", cr_course);
		model.addAttribute("brd_key", brd_key);
		model.addAttribute("user_key", user_key);
		
		return "board/del_ct_done";
	}
	
	
}
