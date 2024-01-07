package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import beans.BoardBean;
import beans.CourseBean;

public interface MainMapper {

	@Select("SELECT a.cr_key, a.cr_course, b.sb_category, b.sb_subject, b.sb_photo, b.ct_key "
			+ "FROM COURSE_TABLE a "
			+ "INNER JOIN subject_table b "
			+ "on a.sb_key = b.sb_key")
	public List<CourseBean> getSubjectsAllCategory();
	
	/*메인에서 3개 게시판 가져오는 글 목록.*/
	@Select("SELECT A.BRD_KEY, A.BRD_TITLE, C.USER_NAME, A.BRD_DATE, A.BRD_HIT,  "
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
		List<BoardBean> getMainList(RowBounds rowBounds, int cr_key);
}
