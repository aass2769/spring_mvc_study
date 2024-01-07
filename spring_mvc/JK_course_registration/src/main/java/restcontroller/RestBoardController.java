package restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import beans.BoardBean;
import service.BoardService;

@RestController
public class RestBoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/modifyComment/{brd_ct_key}/{brd_ct_content}")
	public String modifyComment(@PathVariable int brd_ct_key, @PathVariable String brd_ct_content) {
		
		BoardBean modifyCommentBean = new BoardBean();
		modifyCommentBean.setBrd_ct_key(brd_ct_key);
		modifyCommentBean.setBRD_CT_CONTENT(brd_ct_content);
		
		boardService.modifyComment(modifyCommentBean);
		
		return "성공";
	}
}
