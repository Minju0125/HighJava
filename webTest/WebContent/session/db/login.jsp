<%@page import="kr.or.ddit.session.login.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberVO loginMember = (MemberVO)session.getAttribute("loginMember"); 
%>

<%
if(loginMember==null){ //로그인이 안된 상태
%>

	<form action="<%=request.getContextPath()%>/login.do"  method="post">
	<table border="1" style="margin:0 auto;">
		<tr>
			<td>ID : </td>
			<td><input type="text" name="userid" placeholder="ID를 입력하세요." ></td>
			</tr>
		<tr>
			<td>PASS : </td>
			<td><input type="password" name="userpass" placeholder="PassWord를 입력하세요." ></td>
			</tr>
		<tr>
			<td colspan="2"><input type="submit" value="login"></td>
			</tr>
	</table>
</form>

<%
}else{ //로그인 되었을 때
%>
<h2><%=loginMember.getMem_name() %>님 반갑습니다.</h2>
<a href="<%=request.getContextPath()%>/logout.do">로그아웃</a>

<%
}
%>
</body>
</html>