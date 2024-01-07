<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="SignUp, Login, Register">
<meta name="keywords" content="Sign up, Sign in">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<!--Bootstrap Css-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!--Font-aweome-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins&display=swap"
	rel="stylesheet">
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
					
					<!--accounts_forms-->
					<div class="accounts_forms  w-100 h-100" id="login">
						<div class="title  mt-4 p-4 w-100">
							<h1>Find a password</h1>
						</div>
						<!--title-->
						<form:form action="${root }user/forget_pw_pro" method="post" modelAttribute="forgetUserBean" class="form  w-100 p-4" id="form">
							<div class="form-group">
								<form:label path="user_id">ID</form:label>
								<form:input path="user_id" class="form-control"/>
								<form:errors path="user_id" style="color:red"/>
							</div>
							<div class="form-group">
								<form:label path="user_name">Name</form:label>
								<form:input path="user_name" class="form-control"/>
								<form:errors path="user_name" style="color:red"/>
							</div>
							<div class="form-group mb-0">
								<form:button class="btn btn-primary register_btn w-100">Find a password</form:button>
							</div>
							<c:if test="${password != null }">
								<p style="margin-top:20px; margin-bottom:0px;">비밀번호는 <span style="color:#670AC5; font-weight:bold;">${password}</span> 입니다.</p>
							</c:if>
						</form:form>
						<div
							class="already_member_box d-flex justify-content-between px-4">
							<span class="text-center" id="to_signup"><a href="${root }/user/login">Go Login</a></span> 
							<span class="text-center"><a href="${root }/user/join">Go Join</a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
		<!-- 하단 정보 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>