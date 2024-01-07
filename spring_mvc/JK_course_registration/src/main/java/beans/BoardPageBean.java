package beans;

public class BoardPageBean {

	//최소 페이지 번호 = 한 페이지네이션 당 맨 왼쪽 번호
	private int min;
	//최대 페이지 번호 = 한 페이지네이션 당 맨 오른쪽 번호
	private int max;
	//이전 버튼을 누르면 이동 할 페이지 번호
	private int prevPage;
	//다음 버튼을 누르면 이동 할 페이지 번호
	private int nextPage;
	//전체 페이지 갯수
	private int pageCnt;
	//현재 페이지 번호
	private int currentPage;
	
	//contenCnt : 전체 글 갯수, currentPage : 현재 페이지 번호, contentPageCnt : 페이지당 글의 갯수, paginationCnt : 페이지네이션 버튼의 갯수
	public BoardPageBean() {}
	public BoardPageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		
		// 현재 페이지 번호
		this.currentPage = currentPage;
		
		//전체 페이지 갯수
		pageCnt = contentCnt / contentPageCnt;
		
		if(contentCnt % contentPageCnt > 0) { 
			pageCnt++;
		}
		
		
		min = ((currentPage -1) / paginationCnt) * paginationCnt + 1;
		max = min + paginationCnt - 1;	
		
		if(max > pageCnt) { 
			max = pageCnt;
		}
		
		prevPage = min - 1;	
		nextPage = max + 1;	
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
		
	}
	
	
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	
	
}
