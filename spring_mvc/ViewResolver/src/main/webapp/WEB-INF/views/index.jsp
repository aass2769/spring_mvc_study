<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="test1?data1=100&data2=200">test1</a><br/>
	<hr/>
	<form action="test1" method="post">
		<input type="text" name="data1">
		<input type="text" name="data2">
		<button type="submit">전송</button>
	</form>
	<hr/>
	<a href="test2">test2</a><br/>
	<hr/>
	<a href="test3">test3</a><br/>
	<hr/>
	<a href="test4">test4</a><br/>
	
</body>
</html>