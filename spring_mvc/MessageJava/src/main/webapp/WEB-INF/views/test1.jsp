<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test1</h1>
	<h3>aaa.a1 : <spring:message code="aaa.a1"/></h3>
	<h3>bbb.b1 : <spring:message code="bbb.b1"/></h3>
	<!-- arguments를 넣어주면 인자를 넣어준다. -->
	<h3>aaa.a2 : <spring:message code="aaa.a2" arguments="${args }"/></h3>
	<!-- jsp에서는 현재 브라우저꺼를 그대로 적용해주어서 locale을 설정하지 않아도 다국어 처리를 해준다. -->
	<h3>aaa.a3 : <spring:message code="aaa.a3"/></h3>
</body>
</html>