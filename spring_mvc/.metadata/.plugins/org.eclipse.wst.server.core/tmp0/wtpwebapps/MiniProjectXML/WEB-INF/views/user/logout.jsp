<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 로고 클릭 시 메인페이지로 가야하는데 절대경로를 동적으로 지정해주기 위해 url 설정 -->
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<script type="text/javascript">
	alert("로그아웃 되었습니다.")
	location.href="${root}main"
</script>