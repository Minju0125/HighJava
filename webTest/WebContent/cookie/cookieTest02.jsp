<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</style>
</head>
<body>
<h3>cookieTest02.jsp</h3><hr>


	<a href="<%=request.getContextPath()%>/cookieCountInc.do">Cookie Count 증가하기</a><br><br>
	<a href="<%=request.getContextPath()%>/cookieDelServlet.do">Cookie Count 초기화하기</a>


</body>
</html>