<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<!-- 상단 메뉴 부분 -->
<head>

    <style>
        .navbar {
            background-color: #ffffff;
            border-bottom: 5px solid #670AC5;
            margin-bottom: 30px;
        }

        .navbar-brand img {
            max-height: 200px;
            
        }

        .navbar-toggler {
            margin-left: 20px;
        }

        .navbar-nav .nav-link {
            color: #670AC5;
            font-weight: bold;
            line-height: 40px;
        }

        .dropdown-menu a {
            display: block;
        }

        #logo {
            padding-right: 200px;
        }

        .navbar-nav .dropdown-menu {
            display: none;
        }

        .navbar-nav .dropdown:hover .dropdown-menu {
            display: block;
        }

        .navbar-nav .dropdown-item:hover {
            background-color: #f8f9fa;
        }
    </style>
</head>

    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container px-4">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav flex-fill">
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="#" id="navbarDropdownMenuLink" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #670AC5; margin-top: 100px;">
                            수업과정
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="color: #35016D;">
                        	<c:forEach var="course" items="${course_list}">
                            	<a class="dropdown-item" href="${root}course/subjects?sb_category=${course.sb_category}">${course.cr_course}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="#" id="navbarDropdownMenuLink" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #670AC5; margin-top: 100px;">
                            게시판
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="color: #35016D;">
                        	<c:forEach var="course" items="${course_list}">
                            	<a class="dropdown-item" href="${root}board/detail?cr_key=${course.cr_key}&cr_course=${course.cr_course}">${course.cr_course}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root }course/registration" style="color: #670AC5; margin-top: 100px;">수강신청</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root }course/registration_check" style="color: #670AC5; margin-top: 100px;">수강조회</a>
                    </li>
                </ul>
                <a class="navbar-brand" href="${root }main" id="logo">
                    <img src="${root }image/jklogo.jpg" alt="Logo">
                </a>
                <div class="flex-fill d-flex justify-content-end">
                	<div class="d-flex flex-column">
		                <ul class="navbar-nav">
		                	<c:if test="${userSession.userLogin == true}">
		                		<li class="nav-item" style="font-weight: bold; margin-top: 100px;">${userSession.user_name }님 반갑습니다</li>
		                	</c:if>
		                </ul>
		                <ul class="navbar-nav">
							<c:choose>
								<c:when test="${userSession.userLogin == true}">
							       <li class="nav-item"><a class="nav-link" href="${root}user/modify" style="color: #670AC5;">정보수정</a></li>
							       <li class="nav-item"><a class="nav-link" href="${root}user/logout" style="color: #670AC5;">로그아웃</a></li>
							   </c:when>
							   <c:when  test="${userSession.userLogin == false}">
							   		<li class="nav-item"><a class="nav-link" href="${root}user/join" style="color: #670AC5; margin-top: 100px;">회원가입</a></li>
									<li class="nav-item"><a class="nav-link" href="${root}user/login" style="color: #670AC5; margin-top: 100px;">로그인</a></li>
							   </c:when>
							</c:choose>
						<!--<li class="nav-item"><a class="nav-link"
							href="${root}user/login" style="color: #670AC5; margin-top: 100px;">로그아웃</a>
						</li>-->
						</ul>
					</div>
				</div>
            </div>
        </div>
    </nav>


