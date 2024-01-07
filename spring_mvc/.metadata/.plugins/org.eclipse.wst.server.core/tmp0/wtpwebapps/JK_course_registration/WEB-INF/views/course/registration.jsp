<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
.course_th {
	height: 40px; /* 원하는 높이로 설정하세요. */
	line-height: 40px; /* height 값과 동일하게 설정하세요. */
	text-align: center;
	border-top: 3px solid #F2F2F2;
}

.center-text {
	height: 30px; /* 원하는 높이로 설정하세요. */
	line-height: 30px; /* height 값과 동일하게 설정하세요. */
	text-align: center;
}

.registration_h2{
	padding-bottom : 20px;
}

</style>

<script>

function subjectChange() {
    var sb_category = $("#sb_category").val();
    $.ajax({
        url: "${root}course/subjectList/" + sb_category,
        type: "get",
        dataType: "json",
        success: function (data) {
            // 기존에 추가된 option 제거
            $('#sb_key').empty();
            // 과목명 전체선택 option
            $('#sb_key').append('<option value="0">과목명전체선택</option>');
            // 받아온 데이터를 기반으로 option을 동적으로 추가
            $(data).each(function () {
                $('#sb_key').append('<option value="' + this.sb_key + '">' + this.sb_subject + '</option>');
            });
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}
</script>

</head>
  <body>
  
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
	<section class="py-5 text-center container ">
	    <div class="row py-lg-5">
		<h1 class="text-start registration_h2" style="border-bottom:2px solid #670AC5;">수강신청</h1>
		<div class="col-lg-12 col-md-8 mx-auto" style="margin-top:15px;">
			<div class="card shadow">
				<div class="card-body">
					<div class="search-container">
						<!-- 카테고리 선택(select 옵션) -->
						<form action="${root }course/registration" method="get">
							<div class="row" style="padding-top: 10px;">
							  <div class="col">
							  	<select id="sb_category" name="sb_category" class="form-select" aria-label="Default select" onchange="subjectChange()">
							  		<option value="0" selected>과정종류전체선택</option>
									<c:forEach var="course" items="${courseList }">
										<option value="${course.sb_category }">${course.cr_course }</option>
									</c:forEach>
							  	</select>
							  </div>
							  <div class="col">
								<select id="sb_key" name="sb_key" class="form-select" aria-label="Default select">
									<option value="0" selected>과목명전체선택</option>
								</select>
							  </div>
							  <div class="col-1">
							    <button type="submit" class="btn btn-primary mb-3" style="background-color:#670AC5; border: 2px solid #670AC5; width:78px; margin-right:20px;">검색</button>
							  </div>
							</div>
						</form>	
					</div>   
				</div>
			</div>
	      </div>	
	      <div class="col-lg-12 col-md-8 mx-auto d-flex flex-column justify-content-center align-items-center" style="margin-top:50px;">
		      <div class="table small" style="width: 100%;">
		      	<h4 style="text-align: left;">총 ${subjectCount }건</h4>
		        <table class="table table-striped table-sm">
		          <thead>
		            <tr>
		              <th class="course_th" scope="col">과정분류</th>
		              <th class="course_th" scope="col">과목명</th>
		              <th class="course_th" scope="col">교육기간</th>
		              <th class="course_th" scope="col">인원</th>
		              <th class="course_th" scope="col">수강신청</th>
		            </tr>
		          </thead>
		          <tbody>
		          	<c:forEach var="registration" items="${duplicateCheckRgList }" varStatus="loop">
			            <tr>
			              <td class="center-text">${registration.cr_course }</td>
			              <td class="center-text" id="sb_subject">${registration.sb_subject }</td>
			              <td class="center-text">${registration.sb_start_date } ~ ${registration.sb_end_date }</td>
			              <td class="center-text">${registration.sb_user_count }/${registration.sb_number_people }</td>
			              <td class="center-text">
			              	<a href="${root }course/subject_view?cr_key=${registration.cr_key}"><button type="button" class="btn btn-sm btn-secondary">커리큘럼</button></a>
			              	<button type="button" class="btn btn-sm" data-bs-toggle="modal" data-bs-target="#${registration.sb_subject }" style="background-color:#A566FF; color:white;">
							  수강신청
							</button>
			              </td>
			              <!-- Modal -->
			            </tr>
			            <div class="modal fade" id="${registration.sb_subject }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog modal-dialog-centered">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h1 class="modal-title fs-5" id="exampleModalLabel">수강신청</h1>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body">
							        <h4>${registration.sb_subject }</h4><h5> 과정을 수강신청 하시겠습니까?</h5>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
							        <form action="${root }course/registration_pro " method="post">
								        <input type="hidden" name="sb_key" value="${registration.sb_key }"/>
								        <c:if test="${registration.sb_user_count == registration.sb_number_people }">
								        	<input name="over" type="hidden" value="false">
								        </c:if>
								        <button type="submit" class="btn btn-primary">신청</button>
							        </form>
							      </div>
							    </div>
							  </div>
						  </div>
			         </c:forEach>
		          </tbody>
		        </table>
		      </div>
	      </div>
	    </div>
  </section>
	
	
	<!-- 하단 정보 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>