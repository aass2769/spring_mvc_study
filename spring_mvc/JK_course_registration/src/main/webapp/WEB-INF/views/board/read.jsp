<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JK_course_registration</title>
<!-- 폰트어썸 CDN  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<script>
function changeToInput(loopId, commentId, buttonId, brdId) {
    // <p> 태그 요소 가져오기
    var paragraph = document.getElementById(commentId);
    
    var brd = document.getElementById(brdId);
    var brd_ct_key = brd.value;
    
    // 새로운 <input> 태그 생성
    var inputElement = document.createElement("textarea");

    // 기존 <p> 태그의 속성 복사
    inputElement.setAttribute("id", paragraph.id);
    inputElement.textContent = paragraph.textContent; // <textarea>의 내용을 기존 <p>의 텍스트 내용으로 설정

    // <p> 태그를 <textarea> 태그로 교체
    paragraph.parentNode.replaceChild(inputElement, paragraph);

    // <textarea>의 스타일 조정 예시
    inputElement.style.width = "800px"; // 너비 조정
    inputElement.style.height = "100px"; // 높이 조정
    inputElement.style.border = "1px solid #ccc"; // 테두리 스타일 조정
    inputElement.style.resize = "none"; // 크기 조정 비활성화
    
    // 수정 버튼을 숨김
    document.getElementById(buttonId).style.display = "none";

    // 제출 버튼 생성
    var submitButton = document.createElement("button");
    submitButton.textContent = "수정";
    submitButton.onclick = function() {
    	var brd_ct_content = inputElement.value;
        // 여기에서 폼 제출을 처리하는 함수를 호출하거나 필요한 작업을 수행
        submitForm(brd_ct_key, brd_ct_content);
        // 폼 제출 후 다시 <p> 태그로 변경
        inputElement.parentNode.replaceChild(paragraph, inputElement);
        // 수정 버튼 다시 표시
        document.getElementById(buttonId).style.display = "inline-block";
        // 취소 버튼 제거
        cancelButton.parentNode.removeChild(cancelButton);
        // 제출 버튼 제거
        submitButton.parentNode.removeChild(submitButton);
    };

    // 제출 버튼을 컨테이너에 추가
    document.getElementById(loopId).appendChild(submitButton);

    // 취소 버튼 생성
    var cancelButton = document.createElement("button");
    cancelButton.textContent = "취소";
    cancelButton.onclick = function() {
        // 취소 버튼을 누르면 다시 <p> 태그로 변경
        inputElement.parentNode.replaceChild(paragraph, inputElement);
        // 수정 버튼 다시 표시
        document.getElementById(buttonId).style.display = "inline-block";
        // 취소 버튼 제거
        cancelButton.parentNode.removeChild(cancelButton);
        // 제출 버튼 제거
        submitButton.parentNode.removeChild(submitButton);
    };

    // 취소 버튼을 컨테이너에 추가
    document.getElementById(loopId).appendChild(cancelButton);
}

// 나중에 폼 제출을 처리하는 함수
function submitForm(brd_ct_key, brd_ct_content) {
    // 여기서 폼을 실제로 제출하거나 원하는 동작을 수행
    
    /* 서버에 비동기적으로 요청을 보내는 ajax통신 */
    $.ajax({
       url : "${root}board/modifyComment/" + brd_ct_key + "/" + brd_ct_content ,
       type : "get",
       /* 서버에서 반환되는 데이터 타입을 text로 지정함 */
       dataType : "text",
       /* 서버에서 성공적으로 응답을 받았을 시 실행됨 */
       success : function(result){
    	   location.reload();
		}
    })

}
</script>
<style>

.post {
	background-color: #ffffff;
	border-radius: 5px;
	margin-bottom: 20px;
}

.icons {
	display: flex;
	align-items: center;
}

.icon {
	margin-right: 10px;
}

.comments {
	margin-top: 30px;
}

.comment {
	margin-bottom: 20px;
}

.divider {
	border-top: 1px solid #35016D;
	margin-top: 20px;
	margin-bottom: 20px;
}

.sort-buttons {
	display: flex;
	justify-content: flex-end;
	margin-bottom: 20px;
}

.sort-button {
	background: transparent;
	border: none;
	cursor: pointer;
	font-size: 16px;
	margin-right: 20px;
	color: #35016D;
}

.sort-button.active {
	font-weight: bold;
}

.sort-button:focus {
	outline: none;
}

.comment-header {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 20px;
}

.comment-header h3 {
	margin: 0;
	text-align: left;
}

#commentContent::placeholder {
	/* placeholder 텍스트의 색상을 지정합니다. */
	color: #1D202E; /* 흰색 등 원하는 색상으로 변경 가능 */
}

.btn-secondary.mr-2 {
	margin-left: 0px; /* 원하는 여백 크기로 설정 */
}

</style>

</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div class="container">
		<div class="post">
		<a href="${root}board/detail?cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}" class="btn btn-link text-dark">${readBoard.cr_course}</a>
			<h2>${readBoard.brd_title}</h2>
			<p>${readBoard.user_name}</p>
			<p>${readBoard.brd_date} 조회 ${readBoard.brd_hit}</p>
			<hr>
				<c:if test="${readBoard.brd_file != null}">
					<div class="form-group">
					<img src="${root}upload/${readBoard.brd_file}" width="300px" height="300px"/>
					</div>
				</c:if>
			<p>${readBoard.brd_content}</p>
			<div class="icons">
				<div class="icon">
					<i class="fa-solid fa-heart"></i> <span>${readBoard.brd_likes_count}</span>
				</div>
				<div class="icon">
					<i class="fas fa-comment"></i> <span>${totalComment}</span>
				</div>
			</div>
			<div class="text-center">
			<!-- 좋아요 안 했을 시,  비워진 좋아요 하트 = 좋아요 버튼 -->
			<c:choose>
				<c:when test="${chkLike == false}">
					<form action="${root}board/addLike"  method="post">
						<input type="hidden" value="${readBoard.brd_key}" name="brd_key">
							<button class="btn btn-light"
								style="background-color: #670AC5; color: #ffffff;">
								<i class="fa-regular fa-heart"></i> 좋아요
							</button>
						</form>
				</c:when>
				<%--좋아요 했을 시,  채워진 좋아요 하트 = 좋아요 취소 버튼 --%>
				<c:otherwise>
					<form action="${root}board/deleteLike"  method="post">
							<input type="hidden" value="${readBoard.brd_key}" name="brd_key">
								<button class="btn btn-light"
									style="background-color: #670AC5; color: #ffffff;">
									<i class="fa-solid fa-heart" style="color : red;"></i> 좋아요
								</button>
						</form>
				</c:otherwise>
				</c:choose>
			</div>
			<c:choose>
				<c:when test="${readBoard.brd_writer == user_key}">
					<div class="d-flex justify-content-end">
	                        <a href="${root}board/modify?brd_key=${readBoard.brd_key}"><button class="btn btn-link text-dark mr-2">수정</button></a>
	                        <a href="${root}board/delete?brd_key=${readBoard.brd_key}&cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}&brd_like_key=${readBoard.brd_like_key}"><button class="btn btn-link text-dark">삭제</button></a>
	                 </div>
                 </c:when>
                 <c:otherwise>
                 </c:otherwise>
            </c:choose>
		</div>
		<%--댓글 등록/최신순 버튼 --%>
		<div class="divider"></div>
		<div class="comments">
			<div class="comment-header">
				<h3>댓글 ${totalComment}</h3>
				<div class="sort-buttons">
					<c:choose>
						<c:when test="${sort == '등록순' }">
							<a href="${root}board/read?brd_key=${readBoard.brd_key}&cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}&user_key=${user_key}">
								<button class="sort-button active" id="sort-oldest">등록순</button>
							</a>
							<a href="${root}board/read?brd_key=${readBoard.brd_key}&cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}&user_key=${user_key}&sort=최신순">
								<button class="sort-button" id="sort-newest">최신순</button>
							</a>
						</c:when>
						<c:otherwise>
							<a href="${root}board/read?brd_key=${readBoard.brd_key}&cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}&user_key=${user_key}">
								<button class="sort-button" id="sort-oldest">등록순</button>
							</a>
							<a href="${root}board/read?brd_key=${readBoard.brd_key}&cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}&user_key=${user_key}&sort=최신순">
								<button class="sort-button active" id="sort-newest">최신순</button>
							</a>
						</c:otherwise>
					</c:choose>
					
				</div>
			</div>
			<%-- 댓글  수정과 삭제--%>
			<c:forEach var="comment" items="${commentList}" varStatus="loop">
				<input type="hidden" value="${comment.brd_ct_key}" id="brd_ct_key${loop.index}">
				<div id="comment${loop.index }" class="comment">
						<p>${comment.user_name}</p>
						<p id="myParagraph${loop.index }">${comment.BRD_CT_CONTENT}</p>
						<div class="d-flex justify-content-between">
							<p>${comment.brd_ct_date}</p>
							<c:if test="${user_key == comment.user_key}">
								<div class="d-flex justify-content-between align-items-center">
										<div class="d-flex justify-content-end">
					                        <button id="myButton${loop.index}" type="button"  onclick="changeToInput('comment${loop.index }', 'myParagraph${loop.index }', 'myButton${loop.index}', 'brd_ct_key${loop.index}')"  class ="btn btn-link text-dark mr-2">수정</button>
					                        <a href="${root}board/deleteComment?cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}&brd_ct_key=${comment.brd_ct_key}&brd_key=${readBoard.brd_key}&user_key=${user_key}"><button class="btn btn-link text-dark">삭제</button></a>
			                    		</div>
		                	</div> 
							</c:if>
							</div>
				</div>
				<div class="divider divider-1" style="border-top-color: #ede8f1;"></div>
			</c:forEach>
			</div>
			<div class="divider"></div>
			<div class="comment-form">
			<%--댓글 입력 --%>
				<form:form action="${root}board/addComment?brd_key=${readBoard.brd_key}&user_key=${user_key}&cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}" method="post" modelAttribute="addCommentBean" style="position: relative;">
					<div class="form-group">
					<form:textarea path="BRD_CT_CONTENT" class="form-control" id="commentContent" rows="4" placeholder="댓글을 입력하세요."/>
						<!-- 댓글 등록 버튼을 댓글 입력 상자 우측 하단에 삽입 -->
						<form:button class="btn btn-primary"	style="position: absolute; bottom: 0; right: 0; background-color: #670AC5;">
							<i class="fas fa-paper-plane" style="font-size: 20px;"></i>
						</form:button>
					</div>
				</form:form>
			</div>
			<div class="d-flex justify-content-between mt-2">
			<div class="row">
			<form class="form-inline">
				<!-- 글쓰기 버튼 -->
				<a href="${root}board/create"class="btn btn-secondary" style="background-color: #1D202E; margin-right: -5px;">글쓰기</a>
				</form>
			</div>
			<div class="d-flex justify-content-end">
				<!-- 목록 버튼 -->
				<a href="${root}board/detail?cr_key=${readBoard.cr_key}&cr_course=${readBoard.cr_course}"class="btn btn-secondary mr-2" style="background-color: #1D202E;">목록</a>
			</div>
		</div>
		</div>
		


	<!-- 하단 정보 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>