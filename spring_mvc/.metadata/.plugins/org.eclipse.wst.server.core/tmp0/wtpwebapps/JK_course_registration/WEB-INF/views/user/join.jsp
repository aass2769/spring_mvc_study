<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="SignUp, Login, Register">
<meta name="keywords" content="Sign up, Sign in">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JK_course_registration</title>
<!--Bootstrap Css-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!--Font-aweome-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

	/* 아이디 중복확인 함수 */
	function checkDuplicateId(){
		/* #은 특정 HTML요소의 id값을 선택하는 jQuery 문법 */
		var user_id = $("#user_id").val()
		
		if(user_id.length == 0){
			alert("아이디를 입력하세요.")
			return
		}
		
		/* 서버에 비동기적으로 요청을 보내는 ajax통신 */
		$.ajax({
			url : "${root}user/checkDuplicateId/" + user_id,
			type : "get",
			/* 서버에서 반환되는 데이터 타입을 text로 지정함 */
			dataType : "text",
			/* 서버에서 성공적으로 응답을 받았을 시 실행됨 */
			success : function(result){
				/* trim()은 문자열의 양쪽 끝에있는 공백을 제거함 */
				if(result.trim() == "true"){
					alert("사용할 수 있는 아이디입니다.")
					$("#duplicate_id").val(true)
				} else{
					alert("사용할 수 없는 아이디입니다.")
					$("#duplicate_id").val(false)
				}
			}
		})
	}
	
	/* 아이디 중복확인 리셋함수. 중복확인 후 아이디를 변경하고 가입하는걸 방지하기위해 */
	function resetDuplicateId(){
		$("#duplicate_id").val(false)
	}
</script>

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
							<h1>Sign Up</h1>
						</div>
						<!--title-->
						<form:form action="${root }user/join_pro" method="post" modelAttribute="joinUserBean" name="form" class="form w-100 p-4" id="form" style="padding-top: 0 !important; padding-bottom: 0 !important;">
							<form:hidden path="duplicate_id"/>
							<div class="form-group">
								<form:label path="user_name">Name</form:label>
								<form:input path="user_name" class="form-control" id="name"/>
								<form:errors path="user_name" style="color:red"/>
							</div>
							<!--form-row-->
							<div class="form-group">
								<form:label path="user_id">ID</form:label>
								<div class="input-group">
								<form:input path="user_id" class="form-control" onkeypress="resetDuplicateId()"/>
								<div class="input-group-append">
									<button type="button" class="btn btn-primary" onclick="checkDuplicateId()">중복확인</button>
								</div>
							</div>
							<form:errors path="user_id" style="color:red"/>
							</div>
							<div class="form-group">
								<form:label path="user_pw">Password</form:label> 
								<form:password path="user_pw" class="form-control" id="signup_password"/>
								<form:errors path="user_pw" style="color:red"/>
							</div>
							<div class="form-group">
								<form:label path="user_pw2">Confirm Password</form:label>
								<form:password path="user_pw2" class="form-control" id="cpass"/>
								<form:errors path="user_pw2" style="color:red"/>
							</div>
							<div class="form-group">
								<form:button class="btn btn-primary register_btn w-100">Sign Up</form:button>
							</div>
						</form:form>

						<div class="already_member_box">
							<p class="text-center" id="to_login"><a href="${root }user/login">I am already member</a></p>
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