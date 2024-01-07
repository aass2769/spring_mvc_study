<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<script>
	alert("이미 수강신청한 항목입니다. 다시 확인해주세요")
	location.href="${root}course/registration"
</script>