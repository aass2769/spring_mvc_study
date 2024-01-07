package beans;

/*교육 과정 빈*/
public class CourseBean {

	private int cr_key;
	private String cr_course;
	
	private int sb_key;
	private int sb_category;
	private String sb_subject;
	private String sb_photo;
	private String sb_info_photo;
	private String sb_start_date;
	private String sb_end_date;
	private String sb_number_people;
	
	private int ct_key;
	private int ct_category;
	private String ct_title;
	private String ct_description;
	
	private int rg_key;
	//수강신청한 유저 숫자
	private int sb_user_count;
	
	private int user_key;
	private String user_id;
	
	public int getCr_key() {
		return cr_key;
	}
	public void setCr_key(int cr_key) {
		this.cr_key = cr_key;
	}
	public String getCr_course() {
		return cr_course;
	}
	public void setCr_course(String cr_course) {
		this.cr_course = cr_course;
	}
	public int getSb_key() {
		return sb_key;
	}
	public void setSb_key(int sb_key) {
		this.sb_key = sb_key;
	}
	public int getSb_category() {
		return sb_category;
	}
	public void setSb_category(int sb_category) {
		this.sb_category = sb_category;
	}
	public String getSb_subject() {
		return sb_subject;
	}
	public void setSb_subject(String sb_subject) {
		this.sb_subject = sb_subject;
	}
	public String getSb_photo() {
		return sb_photo;
	}
	public void setSb_photo(String sb_photo) {
		this.sb_photo = sb_photo;
	}
	public String getSb_info_photo() {
		return sb_info_photo;
	}
	public void setSb_info_photo(String sb_info_photo) {
		this.sb_info_photo = sb_info_photo;
	}
	public String getSb_start_date() {
		return sb_start_date;
	}
	public void setSb_start_date(String sb_start_date) {
		this.sb_start_date = sb_start_date;
	}
	public String getSb_end_date() {
		return sb_end_date;
	}
	public void setSb_end_date(String sb_end_date) {
		this.sb_end_date = sb_end_date;
	}
	public String getSb_number_people() {
		return sb_number_people;
	}
	public void setSb_number_people(String sb_number_people) {
		this.sb_number_people = sb_number_people;
	}
	public int getCt_key() {
		return ct_key;
	}
	public void setCt_key(int ct_key) {
		this.ct_key = ct_key;
	}
	public int getCt_category() {
		return ct_category;
	}
	public void setCt_category(int ct_category) {
		this.ct_category = ct_category;
	}
	public String getCt_title() {
		return ct_title;
	}
	public void setCt_title(String ct_title) {
		this.ct_title = ct_title;
	}
	public String getCt_description() {
		return ct_description;
	}
	public void setCt_description(String ct_description) {
		this.ct_description = ct_description;
	}
	public int getRg_key() {
		return rg_key;
	}
	public void setRg_key(int rg_key) {
		this.rg_key = rg_key;
	}
	public int getUser_key() {
		return user_key;
	}
	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}
	public int getSb_user_count() {
		return sb_user_count;
	}
	public void setSb_user_count(int sb_user_count) {
		this.sb_user_count = sb_user_count;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
}
