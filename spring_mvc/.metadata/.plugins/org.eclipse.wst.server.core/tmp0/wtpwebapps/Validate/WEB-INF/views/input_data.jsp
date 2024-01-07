<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>input_data</h1>
	<form action="input_pro" method="post">
		data1 : <input type="text" name="data1"/><br/>
		<spring:hasBindErrors name="dataBean1">
		<!-- result가 errors라는 이름으로 request영역에 담겨서 jsp로 전달됨 -->
		<!-- errors객체가 data1 필드에 대한 오류를 가지고있는지 여부를 검사하고 오류가 있으면 기본메세지를 출력한다. -->
			<c:if test="${errors.hasFieldErrors('data1') }">
				${errors.getFieldError("data1").defaultMessage }<br/>
			</c:if>
		</spring:hasBindErrors>
		data2 : <input type="text" name="data2"/><br/>
		<spring:hasBindErrors name="dataBean1">
			<c:if test="${errors.hasFieldErrors('data2') }">
				${errors.getFieldError("data2").defaultMessage }<br/>
			</c:if>
		</spring:hasBindErrors>
		<button type="submit">확인</button>
	</form>
</body>
</html>