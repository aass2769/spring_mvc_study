<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<script>
	alert("댓글이 삭제 되었습니다.")
	location.href="${root}board/read?cr_key=${cr_key}&cr_course=${cr_course}&brd_key=${brd_key}&user_key=${user_key}"
</script>