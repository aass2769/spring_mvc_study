package com.woomin.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.woomin.beans.ContentBean;
import com.woomin.beans.PageBean;
import com.woomin.beans.UserBean;
import com.woomin.dao.BoardDao;

@Service
public class BoardService {

	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	private String saveUploadFile(MultipartFile upload_file) {
		
		//파일 이름을 구하는 변수. 1970년1월1일부터 현재까지 경과한 시간_파일이름
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			//파일을 저장. 파일이 저장될 위치/파일이름
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return file_name;
	}
	
	public void addContentInfo(ContentBean writeContentBean) {
		
		//System.out.println(writeContentBean.getContent_subject());
		//System.out.println(writeContentBean.getContent_text());
		//클라이언트에서 파일선택을 하지않아도 mulitpart객체가 넘어오기 때문에 확인을 위해 size을 콘솔에 출력
		//System.out.println(writeContentBean.getUpload_file().getSize());
		
		MultipartFile upload_file = writeContentBean.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			writeContentBean.setContent_file(file_name);
		}
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		
		boardDao.addContentInfo(writeContentBean);
	}
	
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	public List<ContentBean> getContentList(int board_info_idx, int page){
		
		//RowBound는 인덱스가 0부터 시작하기때문에 0 ~ 9까지 10개 10 ~ 19까지 10개 20 ~ 29까지 10개가됌.
		//그러므로 start는 0, 10, 20이 되어야함
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		
		return boardDao.getContentList(board_info_idx, rowBounds);
	}
	
	public ContentBean getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}
	
	public void modifyContentInfo(ContentBean modifyContentBean) {
		
		MultipartFile upload_file = modifyContentBean.getUpload_file();
		
		if(upload_file.getSize() > 0){
			String file_name = saveUploadFile(upload_file);
			modifyContentBean.setContent_file(file_name);
		}
		
		boardDao.modifyContentInfo(modifyContentBean);
	}
	
	public void deleteContentInfo(int content_idx, String content_name) {
		deleteFile(content_name);
		boardDao.deleteContentInfo(content_idx);
	}
	
	public void deleteFile(String content_name) {
		
		String filePath = path_upload + "/" + content_name;
		
		File fileToDelete = new File(filePath);
		
		if(fileToDelete.exists()) {
			fileToDelete.delete();
		}
	}
	
	public PageBean getContentCnt(int content_board_idx, int currentPage) {
		
		int content_cnt = boardDao.getContentCnt(content_board_idx);
		
		PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageBean;
	}
}
