<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String sessionValue = (String)session.getAttribute("userId");
	
	if()
%>


<body>
<form action="<%=request.getContextPath()%>/sessionLogin.do"  method="post">
	<table>
		<tr>
			<td>ID : </td>
			<td><input type="text" name="id"></td>
			</tr>
		<tr>
			<td>PASS : </td>
			<td><input type="password" name="pass" ></td>
			</tr>
		<tr>
			<td colspan="2"><input type="submit" value="login"></td>
			</tr>
	</table>
</form>
</body>
</html>