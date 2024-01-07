<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<script>
	alert("작성 되었습니다.")
	location.href="${root}board/detail?cr_key=${addBoardBean.cr_key}&cr_course=${cr_course}"
</script>