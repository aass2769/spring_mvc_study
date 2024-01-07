<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JK_course_registration</title>
<!-- Bootstrap CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<style>
	.fw-light{
		font-weight: 700 !important;
		color:#1D202E;
	}
	
	.curriculum_container{
		border : 1px solid #f1f3f5;
	}
	
	.curriculum_top_row{
		padding-top : 15px;
		padding-bottom : 15px;
		background-color : #f8f9fa;
	}
	
	.curriculum_row{
		border-bottom : 1px solid #f1f3f5;
	}
	
	.subject_h1{
		margin-bottom : 30px !important;
	}
	
	.sb_button_div{
		margin-top : 20px;
	}
</style>
</head>
  <body>
  
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<c:forEach var="subject" items="${subjectInfoList }" varStatus="loop">
		<c:if test="${loop.index == 0 }">
		  	<section class="py-5 text-center container ">
		    <div class="row py-lg-5" style="height: 50vh;">
		      <div class="col-lg-10 col-md-8 mx-auto" style="margin: auto;">
		        <h1 class="fw-light subject_h1">${subject.sb_subject } 수업과정</h1>
		        <div class="container text-center">
					<img src="${root }image/${subject.sb_info_photo }.jpg" class="card-img-top"	alt="Course Image" style=" height: 40vh;">
				</div>
				<div class="sb_button_div">
					<a href="${root }course/subjects?sb_category=${subject.sb_category}" class="btn btn-secondary my-2">${subject.cr_course } 과정보기</a>
					<a href="${root }course/registration" class="btn btn-primary my-2" style="background-color : #670AC5; border : 1px solid #670AC5;">수강신청</a>
		        </div>
		      </div>
		      </div>
			</section>
		</c:if>
	</c:forEach>
  	<section class="py-5 text-center container ">
    <div class="row py-lg-5">
      <div class="col-lg-10 col-md-8 mx-auto" style="margin: auto;">
        <h1 class="fw-light subject_h1">커리큘럼</h1>
        <div class="container text-center curriculum_container">
		  <div class="row curriculum_top_row">
		    <div class="col-1">
		      단계
		    </div>
		    <div class="col-5">
		      제목
		    </div>
		    <div class="col-6">
		      내용
		    </div>
		  </div>
		  <c:forEach var="sb_info" items="${subjectInfoList }" varStatus="loop">
			 <div class="row curriculum_row" style="padding-bottom: 6px; padding-top: 6px;">
			     <div class="col-1">
					${loop.index + 1 }
			     </div>
			     <div class="col-5">
			    	${sb_info.ct_title }
			     </div>
		    	 <div class="col-6">
		    		${sb_info.ct_description }
		    	 </div>
		  	</div>
		  </c:forEach>
		 
		</div>
      </div>
    </div>
  </section>
  
	<!-- 하단 정보 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>