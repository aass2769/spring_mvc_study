<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>input_data</h1>
	<form:form action="input_pro" modelAttribute="dataBean1" method="post">
		data1 : <form:input path="data1" type="text"/><br/>
		<!-- form:errors를 사용하면 
		<spring:hasBindErrors name="dataBean1">
			<c:if test="${errors.hasFieldErrors('data1') }">
				<spring:message code="${errors.getFieldError('data1').codes[0] }"/><br/>
			</c:if>
		</spring:hasBindErrors> 를 생략할 수 있다.위 코드와 동일한 효과를 보임-->
			<form:errors path="data1"/><br/>
		data2 : <form:input path="data2" type="text"/><br/>
			<form:errors path="data2"/><br/>
		<form:button type="submit">확인</form:button>
	</form:form>
	
	<%--
	<form action="input_pro" method="post">
		data1 : <input type="text" name="data1" /><br />
		<!-- 만약 dataBean1에 오류가 있다면 그리구 data1에 오류가 있다면 에러메시지를 보여주게함. -->
		<spring:hasBindErrors name="dataBean1">
			<c:if test="${errors.hasFieldErrors('data1') }">
				<!-- BingingResult객체의 FieldError에는 codes라는 배열을 명시적으로 생성하지 않아도 자동 생성되는 속성이 있다. -->
				<spring:message code="${errors.getFieldError('data1').codes[0] }"/><br/>
			</c:if>
		</spring:hasBindErrors>
		data2 : <input type="text" name="data2" /><br />
		<spring:hasBindErrors name="dataBean1">
			<c:if test="${errors.hasFieldErrors('data2')}">
				<spring:message code="${errors.getFieldError('data2').codes[0] }"/><br/>
			</c:if>
		</spring:hasBindErrors>
		<button type="submit">확인</button>
	</form>
	--%> 
</body>
</html>