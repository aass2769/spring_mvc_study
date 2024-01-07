<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JK_course_registration</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<section class="login_section">
		<div class="container outer_container accounts_container">
			<div class="row h-100" style="margin-top: 70px;">
				<div
					class="col col-sm-12 col-md-12 col-lg-8 m-0 p-0 w-100 h-100 accounts_col">
					<div class="accounts_image w-100 h-100">
						<img src="${root}image/loginlogo.jpg" width="755" height="505" />
					</div>
					<!--accounts_image-->
				</div>
				<!--account_col-->
				<div class="col col-sm-12 col-md-12 col-lg-4 m-0 p-0 accounts_col">
					<div class="accounts_forms signup_form w-100 h-100" id="signup">
						<div class="title mt-4 p-4 w-100" style="padding-top: 0 !important; padding-bottom: 0 !important;">
							<h1>정보 수정</h1>
						</div>
						<!--title-->
						<form:form action="${root }user/modify_pro" method="post" modelAttribute="modifyUserBean" class="form w-100 p-4" style="padding-top: 0 !important; padding-bottom: 0 !important;">
						<div class="form-group">
							<form:label path="user_name">Name</form:label>
							<form:input path="user_name" class="form-control" readonly="true"/>
						</div>
						<div class="form-group">
							<form:label path="user_id">ID</form:label>
							<form:input path="user_id" class="form-control" readonly="true"/>
						</div>
						<div class="form-group">
							<form:label path="user_pw">Password</form:label>
							<form:password path="user_pw" class="form-control"/>
							<form:errors path="user_pw" style="color:red"/>
						</div>
						<div class="form-group">
							<form:label path="user_pw2">Confirm Password</form:label>
							<form:password path="user_pw2" class="form-control"/>
							<form:errors path="user_pw2"  style="color:red"/>
						</div>
						<div class="form-group">
							<div class="text-right">
								<form:button class="btn btn-primary register_btn w-100">수정</form:button>
							</div>
						</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</section>

		<!-- 하단 정보 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>