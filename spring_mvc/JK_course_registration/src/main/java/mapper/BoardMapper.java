package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import beans.BoardBean;

public interface BoardMapper {

	/*글 목록.*/
	@Select("SELECT A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT, "
					+ "SUM(B.brd_likes_count) AS total_likes_count "
					+ "FROM board_table A "
					+ "LEFT OUTER JOIN  "
					+ "(SELECT BRD_KEY, COUNT(USER_KEY) AS brd_likes_count "
					+ "FROM BOARD_LIKE_TABLE "
					+ " GROUP BY BRD_KEY "
					+ ") B ON A.BRD_KEY = B.BRD_KEY "
					+ "LEFT OUTER JOIN USER_TABLE C ON A.USER_KEY = C.USER_KEY "
					+ "WHERE A.CR_KEY = #{CR_KEY} "
					+ "GROUP BY A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT "
					+ "ORDER BY A.BRD_KEY DESC")
		List<BoardBean> getBoardList(int cr_key, RowBounds rowBounds);
	
	//전체 페이지네이션
	@Select("select count(*) from board_table where cr_key = #{cr_key}")
	String getTotalContentCnt(int cr_key);
	
	//글작성자로 검색하여 페이지네이션
	@Select("SELECT SUM(count(*)) AS content_cnt "
			+ "FROM board_table A "
			+ "LEFT OUTER JOIN USER_TABLE B ON A.USER_KEY = B.USER_KEY "
			+ "WHERE A.CR_KEY = #{cr_key} AND B.USER_NAME LIKE '%' || #{user_name} || '%' "
			+ "GROUP BY A.BRD_KEY "
			+ "ORDER BY A.BRD_KEY DESC")
	String getUserContentCnt(@Param("cr_key")int cr_key, @Param("user_name")String user_name);
	
	//제목으로 검색하여 페이지네이션
	@Select("SELECT SUM(count(*)) AS content_cnt "
			+ "FROM board_table A "
			+ "LEFT OUTER JOIN USER_TABLE B ON A.USER_KEY = B.USER_KEY "
			+ "WHERE A.CR_KEY = #{cr_key} AND A.brd_title LIKE '%' || #{brd_title} || '%' "
			+ "GROUP BY A.BRD_KEY "
			+ "ORDER BY A.BRD_KEY DESC")
	String getTitleContentCnt(@Param("cr_key")int cr_key, @Param("brd_title")String brd_title);
	
	//게시글로 검색하여 페이지네이션
	@Select("SELECT SUM(count(*)) AS content_cnt "
			+ "FROM board_table A "
			+ "LEFT OUTER JOIN USER_TABLE B ON A.USER_KEY = B.USER_KEY "
			+ "WHERE A.CR_KEY = #{cr_key} AND A.brd_content LIKE '%' || #{brd_content} || '%' "
			+ "GROUP BY A.BRD_KEY "
			+ "ORDER BY A.BRD_KEY DESC")
	String getContentCnt(@Param("cr_key")int cr_key, @Param("brd_content")String brd_content);
	
	//글작성자, 제목, 내용 전체로 검색하여 페이지네이션
	@Select("SELECT SUM(count(*)) AS content_cnt "
			+ "FROM board_table A "
			+ "LEFT OUTER JOIN USER_TABLE B ON A.USER_KEY = B.USER_KEY "
			+ "WHERE A.CR_KEY = #{cr_key} AND (B.USER_NAME LIKE '%' || #{user_name} || '%' OR "
			+ "A.brd_content LIKE '%' || #{brd_content} || '%' OR "
			+ "A.brd_title LIKE '%' || #{brd_title} || '%' ) "
			+ "GROUP BY A.BRD_KEY "
			+ "ORDER BY A.BRD_KEY DESC")
	String getTotalSearchContentCnt(@Param("cr_key") int cr_key, @Param("user_name") String user_name, @Param("brd_content") String brd_content, @Param("brd_title") String brd_title);
	
	//글 작성 쿼리
	//db에서 null을 허용으로 설정해놨어도 mybatis에서는 허용하지 않기때문에 jdbcType=VARCHAR같이 타입을 명시적으로 작성해줌.
	@Insert("INSERT INTO board_table (BRD_KEY, BRD_WRITER, BRD_DATE, BRD_NOTICE, BRD_TITLE, BRD_CONTENT, BRD_FILE, BRD_HIT, USER_KEY, CR_KEY) " 
			+ "VALUES (board_seq.nextval, #{brd_writer}, sysdate, 'N', #{brd_title}, #{brd_content}, #{brd_file, jdbcType=VARCHAR}, 0, #{user_key}, #{cr_key})" )
		void addBoard(BoardBean addBoardBean);
	
	/*cr_course 가져오는 쿼리..*/
	@Select("select cr_course from course_table where cr_key = #{cr_key}")
	public String selCourse(int cr_key);
	
	/*글 읽기 쿼리*/
	@Select("SELECT a.brd_key, a.brd_title, a.brd_content, a.brd_hit, a.brd_date,  a.brd_writer, a.brd_file,  b.user_name, c.brd_likes_count , d.cr_key, d.cr_course "
			+ "FROM board_table a "
			+ "LEFT OUTER JOIN user_table b ON a.user_key = b.user_key "
			+ "LEFT OUTER JOIN "
			+ "  ( SELECT BRD_KEY, COUNT(*) AS brd_likes_count "
			+ "  FROM board_like_table "
			+ "  GROUP BY BRD_KEY "
			+ ") c ON a.brd_key = c.brd_key "
			+ " LEFT OUTER JOIN course_table d "
			+" ON a.cr_key = d.cr_key "
			+ "WHERE a.brd_key = #{brd_key} ")
	BoardBean readBoard(int brd_key); 
	
	/*글 조회수 증가 쿼리*/
	@Update("update board_table set brd_hit = brd_hit + 1 where brd_key = #{brd_key}")
	public void addHit(int brd_key);
	
	/*글 수정 쿼리*/
	@Update("update board_table set brd_title = #{brd_title}, brd_content = #{brd_content}, BRD_FILE = #{brd_file, jdbcType=VARCHAR} where brd_key = #{brd_key}")
	public void editBoard(BoardBean editBoardBean);
	
	/*글 삭제 쿼리*/
	@Delete("delete from board_table where brd_key = #{brd_key}")
	public void delBoard(int brd_key);
	
	/*댓글 전체 삭제 쿼리(게시글 삭제 시)*/
	@Delete("delete from board_comment_table where brd_key = #{brd_key}")
	public void delAllCmt(int brd_key);
	
	/*좋아요가 있을시의 좋아요 삭제 쿼리*/
	@Delete("delete from board_like_table where brd_key = #{brd_key}")
	public void delBoardLike(int brd_key);
	
	/*게시판 좋아요 버튼 누르면 좋아요 되게 하는 쿼리*/
	@Insert("INSERT INTO BOARD_LIKE_TABLE (BRD_LIKE_KEY, BRD_LIKES_COUNT, BRD_KEY, USER_KEY) " 
					+   "VALUES (brd_like_seq.nextval, 1, #{brd_key}, #{user_key}) ")
	public void addLike(BoardBean addLikeBoardBean);
	
	/*게시판 좋아요 했는지 확인하는 쿼리*/
	@Select("select brd_key, user_key from board_like_table where brd_key = #{brd_key} and user_key = #{user_key} ")
	public BoardBean chkBoardLike(BoardBean chkBoardLikeBean);
	
	/*게시판 좋아요 취소(삭제)하는 쿼리*/
	@Delete("delete from board_like_table where brd_key = #{brd_key} and user_key = #{user_key} ")
	//두개 이상이면 param 씀
	public void deleteLike(@Param("brd_key") int brd_key, @Param("user_key") int user_key);
	
	/*댓글 총 갯수 쿼리*/
	@Select("select count(*) from board_comment_table  where brd_key = #{brd_key} ")
	public int totalComment(int brd_key);
	
	/*댓글 작성 쿼리*/
	@Insert("INSERT INTO BOARD_COMMENT_TABLE(brd_ct_key, BRD_CT_CONTENT, brd_ct_count, brd_ct_date, brd_key, user_key) "
			+ "VALUES (BRD_CT_seq.NEXTVAL, #{BRD_CT_CONTENT}, 1, sysdate, #{brd_key}, #{user_key})")
	public void addComment(BoardBean addCommentBean);
	
	/*댓글 조회+등록순 쿼리*/
	@Select("SELECT A.brd_ct_key, A.BRD_CT_CONTENT, A.brd_ct_count, A.brd_ct_date, A.brd_key, A.user_key, B.user_name "
			+ "FROM board_comment_table A "
			+ "INNER JOIN user_table B ON A.user_key = B.user_key "
			+ "WHERE A.brd_key = #{brd_key} order by a.brd_ct_key asc ")
	public List<BoardBean> ascComment(int brd_key);
	
	/*댓글 최신순 쿼리*/
	@Select("SELECT A.brd_ct_key, A.BRD_CT_CONTENT, A.brd_ct_count, A.brd_ct_date, A.brd_key, A.user_key, B.user_name "
			+ "FROM board_comment_table A "
			+ "INNER JOIN user_table B ON A.user_key = B.user_key "
			+ "WHERE A.brd_key = #{brd_key} order by a.brd_ct_key desc ")
	public List<BoardBean> descComment(int brd_key);
	
	/*댓글 수정 쿼리*/
	@Update("update board_comment_table set BRD_CT_CONTENT = #{BRD_CT_CONTENT} where brd_ct_key = #{brd_ct_key} ")
	public void modifyComment(BoardBean modifyCommentBean);
	
	/*댓글 삭제 쿼리*/
	@Delete("delete from board_comment_table where brd_ct_key = #{brd_ct_key} ")
	public void deleteComment(int brd_ct_key);
	
	/*작성자 이름으로 검색 시 쿼리*/
	@Select("SELECT A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT, "
				+ "SUM(B.brd_likes_count) AS total_likes_count "
				+ "FROM board_table A "
				+ "LEFT OUTER JOIN "
				+ "( SELECT BRD_KEY, COUNT(USER_KEY) AS brd_likes_count "
				+ "    FROM BOARD_LIKE_TABLE "
				+ "    GROUP BY BRD_KEY "
				+ ") B ON A.BRD_KEY = B.BRD_KEY "
				+ "LEFT OUTER JOIN USER_TABLE C ON A.USER_KEY = C.USER_KEY "
				+ "WHERE A.CR_KEY = #{cr_key} AND C.USER_NAME  LIKE '%' || #{user_name} || '%'  "
				+ "GROUP BY A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT "
				+ "ORDER BY A.BRD_KEY DESC ")
	public List<BoardBean> nameSearch(@Param("cr_key") int cr_key, @Param("user_name") String user_name, RowBounds rowBounds);
	
	/*제목으로 검색 시 쿼리*/
	@Select("SELECT A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT, "
				+ "SUM(B.brd_likes_count) AS total_likes_count "
				+ "FROM board_table A "
				+ "LEFT OUTER JOIN "
				+ "( SELECT BRD_KEY, COUNT(USER_KEY) AS brd_likes_count "
				+ "    FROM BOARD_LIKE_TABLE "
				+ "    GROUP BY BRD_KEY "
				+ ") B ON A.BRD_KEY = B.BRD_KEY "
				+ "LEFT OUTER JOIN USER_TABLE C ON A.USER_KEY = C.USER_KEY "
				+ "WHERE A.CR_KEY = #{cr_key} AND A.BRD_TITLE  LIKE '%' || #{brd_title} || '%'  "
				+ "GROUP BY A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT "
				+ "ORDER BY A.BRD_KEY DESC ")
	public List<BoardBean> titleSearch(@Param("cr_key") int cr_key, @Param("brd_title") String brd_title, RowBounds rowBounds);
	
	/*게시글로 검색 시 쿼리*/
	@Select("SELECT A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT, "
				+ "SUM(B.brd_likes_count) AS total_likes_count "
				+ "FROM board_table A "
				+ "LEFT OUTER JOIN "
				+ "( SELECT BRD_KEY, COUNT(USER_KEY) AS brd_likes_count "
				+ "    FROM BOARD_LIKE_TABLE "
				+ "    GROUP BY BRD_KEY "
				+ ") B ON A.BRD_KEY = B.BRD_KEY "
				+ "LEFT OUTER JOIN USER_TABLE C ON A.USER_KEY = C.USER_KEY "
				+ "WHERE A.CR_KEY = #{cr_key} AND A.BRD_CONTENT  LIKE '%' || #{brd_content} || '%'  "
				+ "GROUP BY A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT "
				+ "ORDER BY A.BRD_KEY DESC ")
	public List<BoardBean> contentSearch(@Param("cr_key") int cr_key, @Param("brd_content") String brd_content, RowBounds rowBounds);
	
	/*전체로 검색 시 쿼리*/
	@Select("SELECT A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT, "
				+ "SUM(B.brd_likes_count) AS total_likes_count "
				+ "FROM board_table A "
				+ "LEFT OUTER JOIN "
				+ "( SELECT BRD_KEY, COUNT(USER_KEY) AS brd_likes_count "
				+ "    FROM BOARD_LIKE_TABLE "
				+ "    GROUP BY BRD_KEY "
				+ ") B ON A.BRD_KEY = B.BRD_KEY "
				+ "LEFT OUTER JOIN USER_TABLE C ON A.USER_KEY = C.USER_KEY "
				+ "WHERE A.CR_KEY = #{cr_key} AND (A.BRD_CONTENT LIKE '%' || #{brd_content} || '%'  OR "
				+ "A.BRD_TITLE LIKE '%' || #{brd_title} || '%'  OR "
				+ "C.USER_NAME  LIKE '%' || #{user_name} || '%'  ) "
				+ "GROUP BY A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT "
				+ "ORDER BY A.BRD_KEY DESC ")
	public List<BoardBean> totalSearch(@Param("cr_key") int cr_key, @Param("brd_content") String brd_content, 
																 @Param("user_name") String user_name, @Param("brd_title") String brd_title, RowBounds rowBounds);
}
