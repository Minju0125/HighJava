<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>forward, sendRedirect 연습</h3>
<hr>
<form method="post" action="/webTest/responseTest01.do">
   forward 방식 : <input type="text" name="username">
   <input type="submit" value="확 인">
</form>

<hr>

<form method="post" action="/webTest/responseTest02.do">
   sendRedirect 방식 : <input type="text" name="username">
   <input type="submit" value="확 인">
   
</body>
</html>