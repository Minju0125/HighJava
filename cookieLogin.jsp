<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/cookieLoginServlet.do" method="post">
	<label>ID : </label><input type="text" name="id" placeholder="ID 입력하세요">
	<label>PASS : </label><input type="password" name="pass" placeholder="PassWord 입력하세요">
	<input type="checkbox" name="checkbox">
	<input type="submit" value="login">
</form>

</body>
</html>