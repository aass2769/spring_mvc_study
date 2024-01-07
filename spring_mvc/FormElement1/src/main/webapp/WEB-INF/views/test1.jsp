<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test1</h1>
	<form:form modelAttribute="dataBean" action="result">
		<!-- id와 name이 a1으로 설정되고 dataBean에 있는 a1의 값이 value설정에 주입됨 -->
		<form:hidden path="a1"/>
		<!-- id와 name이 a2으로 설정되고 dataBean에 있는 a2의 값이 value설정에 주입됨 -->
		text : <form:input path="a2"/><br/>
		<!-- id와 name이 a3으로 설정되고 dataBean에 있는 a3의 값은 보이지않음. showpassword를 true로 설정하면 보임 -->
		password : <form:password path="a3" showpassword="true"/><br/>
		textarea : <form:textarea path="a4"/><br/>
		<!-- form:button은 디폴트값이 submit -->
		<form:button disabled="true">확인 버튼</form:button>
	</form:form>
</body>
</html>