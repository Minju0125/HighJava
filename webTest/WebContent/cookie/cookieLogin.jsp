<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
Cookie[] cookieArr = request.getCookies();
String cookieUserId = ""; // 쿠키에 저장된 userid값이 저장될 변수
String chk=""; //체크박스의 체크여부를 나타낼 변수

if(cookieArr!=null){
	for(Cookie cookie : cookieArr){
		if("USERID".equals(cookie.getName())){ //원하는 쿠키이름 찾기
			cookieUserId = cookie.getValue(); //쿠키값 구하기
			chk = "checked";
		}
	}
}

%>

<body>
<h3 style="text-align:center;">Login 페이지</h3>

<form action="<%=request.getContextPath()%>/cookieLoginServlet.do" method="post">

<table style="margin:0 auto;">
	<tr>
		<td>ID : </td>
		<td><input type="text" value="<%=cookieUserId %>" name="userid" placeholder="ID 입력하세요"></td>
	</tr>
	
	<tr>	
		<td>PASS : </td>
		<td><input type="password" name="userpass" placeholder="PassWord 입력하세요"></td>
	</tr>	

	<tr>
		<td colspan="2"><input type="checkbox" name="chkid" value="check"  <%=chk%>>ID 기억하기</td>
	</tr>
	
	<tr>
		<td colspan="2" style="text-align:center;"><input type="submit" value="login"></td>
	</tr>
</table>
</form>

</body>
</html>