package beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

/*게시판, 게시판 좋아요, 게시판 댓글*/
public class BoardBean {
	
	/*게시판*/
	private int brd_key;
	private int user_key; //FK, 게시판 + 게시판 좋아요 + 게시판 댓글 여부
	private int cr_key; //FK, 게시판 + 게시판 좋아요 + 게시판 댓글 여부
	private int brd_writer;
	private String brd_date;
	@NotBlank
	private String brd_title;
	@NotBlank
	private String brd_content;
	private String brd_file; //DB에 저장되어있는 파일이름을 담을 변수.
	private MultipartFile upload_File; //클라이언트가 보낼 파일데이터를 담을 변수.
	//brd_file로 하면 String이기 때문에 에러가 남. Spring mvc에서는 MultipartFile이라는 객체로 만들어서
	//주입으로 하려고 하기때문에 이 필드를 만들어줌.
	private int brd_hit;
	private String user_name;
	private String cr_course;
	private String brd_search_content; //검색 내용
	private String brd_search_category; //검색 종류
	private String brd_all_button; //전체 게시글 버튼
	
	/*게시판 좋아요 여부*/
	private int brd_like_key;
	private int brd_likes_count;
	private int total_likes_count; 
	
	/*게시판 댓글*/ //brd_key와 user_key FK
	private int brd_ct_key;
	private String BRD_CT_CONTENT; //댓글내용
	private int brd_ct_count; //댓글 갯수
	private String brd_ct_date; //댓글 작성 날짜
	
	public BoardBean(){
		this.brd_all_button = "전체글보기";
	}
	
	public String getBrd_all_button() {
		return brd_all_button;
	}

	public void setBrd_all_button(String brd_all_button) {
		this.brd_all_button = brd_all_button;
	}

	public String getBrd_search_category() {
		return brd_search_category;
	}

	public void setBrd_search_category(String brd_search_category) {
		this.brd_search_category = brd_search_category;
	}

	public String getBrd_search_content() {
		return brd_search_content;
	}

	public void setBrd_search_content(String brd_search_content) {
		this.brd_search_content = brd_search_content;
	}

	public int getTotal_likes_count() {
		return total_likes_count;
	}

	public void setTotal_likes_count(int total_likes_count) {
		this.total_likes_count = total_likes_count;
	}

	public int getBrd_key() {
		return brd_key;
	}

	public void setBrd_key(int brd_key) {
		this.brd_key = brd_key;
	}

	public int getUser_key() {
		return user_key;
	}

	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}

	public int getCr_key() {
		return cr_key;
	}

	public void setCr_key(int cr_key) {
		this.cr_key = cr_key;
	}

	public int getBrd_writer() {
		return brd_writer;
	}

	public void setBrd_writer(int brd_writer) {
		this.brd_writer = brd_writer;
	}

	public String getBrd_date() {
		return brd_date;
	}

	public void setBrd_date(String brd_date) {
		this.brd_date = brd_date;
	}

	public String getBrd_title() {
		return brd_title;
	}

	public void setBrd_title(String brd_title) {
		this.brd_title = brd_title;
	}

	public String getBrd_content() {
		return brd_content;
	}

	public void setBrd_content(String brd_content) {
		this.brd_content = brd_content;
	}

	public String getBrd_file() {
		return brd_file;
	}

	public void setBrd_file(String brd_file) {
		this.brd_file = brd_file;
	}

	public int getBrd_hit() {
		return brd_hit;
	}

	public void setBrd_hit(int brd_hit) {
		this.brd_hit = brd_hit;
	}

	public int getBrd_like_key() {
		return brd_like_key;
	}

	public void setBrd_like_key(int brd_like_key) {
		this.brd_like_key = brd_like_key;
	}

	public int getBrd_likes_count() {
		return brd_likes_count;
	}

	public void setBrd_likes_count(int brd_likes_count) {
		this.brd_likes_count = brd_likes_count;
	}

	public int getBrd_ct_key() {
		return brd_ct_key;
	}

	public void setBrd_ct_key(int brd_ct_key) {
		this.brd_ct_key = brd_ct_key;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCr_course() {
		return cr_course;
	}

	public void setCr_course(String cr_course) {
		this.cr_course = cr_course;
	}

	public MultipartFile getUpload_File() {
		return upload_File;
	}

	public void setUpload_File(MultipartFile upload_File) {
		this.upload_File = upload_File;
	}

	
	public String getBRD_CT_CONTENT() {
		return BRD_CT_CONTENT;
	}

	public void setBRD_CT_CONTENT(String bRD_CT_CONTENT) {
		BRD_CT_CONTENT = bRD_CT_CONTENT;
	}

	public String getBrd_ct_date() {
		return brd_ct_date;
	}

	public void setBrd_ct_date(String brd_ct_date) {
		this.brd_ct_date = brd_ct_date;
	}

	public int getBrd_ct_count() {
		return brd_ct_count;
	}

	public void setBrd_ct_count(int brd_ct_count) {
		this.brd_ct_count = brd_ct_count;
	}
	
	
}
