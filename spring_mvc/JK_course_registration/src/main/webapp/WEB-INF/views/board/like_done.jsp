<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<script>
	alert("좋아요 되었습니다.")
	location.href="${root}board/read?brd_key=${addLikeBoardBean.brd_key}"
</script>