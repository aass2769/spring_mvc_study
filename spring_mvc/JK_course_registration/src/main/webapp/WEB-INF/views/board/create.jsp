<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JK_course_registration</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
  <style>
		.create_form {
	      display: flex;
	      justify-content: center;
	      margin-top :"20px";
	      height: 700px;
	      margin: 0;
	    }
	
	    .centered-div {
	      width: 300px; /* 원하는 너비 지정 */
	      height: 200px; /* 원하는 높이 지정 */
	      border: 1px solid #ccc;
	      text-align: center;
	      padding: 20px;
	    }
		
   
        .divider {
            margin: 20px 0;
            border-top: 1px solid #ccc;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }
        
        .form-label{
        font-weight: bold;
        }

    </style>

</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="create_form">
        
        <form:form class="form-inline" method="post" action="${root}board/create_pro" modelAttribute="addBoardBean" enctype="multipart/form-data">
        <h2>게시판 글쓰기</h2>
       <form:button class="btn btn-primary"  style="background-color: #670AC5; margin-left: 740px;">등록</form:button>
        <div class="divider"></div>
        <div class="form-group">
        	<form:label path="cr_course">게시판 선택</form:label>
        	<form:select path="cr_key" class="form-select form-select-lg" aria-label="Default select example">
	            <c:forEach var="course" items="${course_list}">
				  		<form:option value="${course.cr_key}">${course.cr_course}</form:option>
				  </c:forEach>
			</form:select>
        </div>
        <div class="form-group">
        	<form:label path="brd_title">제목</form:label>
        	<form:input path="brd_title"  class="form-control form-control-lg" placeholder="제목을 입력하세요." aria-label=".form-control-lg example" />
        	<form:errors path="brd_title" style="color:red"/>
        </div>
        <div class="form-group">
        	<form:label path="upload_File">파일 첨부</form:label>
        	<form:input path="upload_File" type="file"  class="form-control" accept="image/*"/>
		</div>
        <div class="mb-3">
        	<form:label path="brd_content" style=" font-weight: bold;">내용</form:label>
        	<form:textarea path="brd_content" class="form-control"  rows="10"  style="resize: none;"/>
        	<form:errors path="brd_content" style="color:red"/>
		</div>
		</form:form>
    </div>

	<!-- 하단 정보 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>