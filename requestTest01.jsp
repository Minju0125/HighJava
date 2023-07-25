<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- 
	html 주석 처리 방법
 -->

<%--
	이것은 JSP 주석을 나타낸다.
 --%>
 
 <%
 	// 이 영역은 JSP 문서에서 Java 코드를 사용할 수 있는 영역으로 '스크립트릿'이라고 한다.
 	/* 자바에서 쓰던 방식 그대로 주석 처리 */
 	String name = "홍길동";
 %>
 
 <%--
	 <%=변수명 또는 수식 %> ==> JSP 문서나 변수의 수식의 결과를 출력할 때 사용한다.
 
  --%>
  
  
  <!-- 
  	<form> 태그의 속성
  	1) action ==> <form>태그에서 전송한 데이터를 받아서 처리할 문서명 또는 서블릿 URL (생략하면 현재 문서명으로 설정된다.)
  	2) method ==> 전송방식 (GET, POST) ==> 기본값 : GET
  	3) target ==> 응답이 나타날 프레임
  	4) enctype ==> 서버로 파일을 전송할 때 이 속성에 'multipart/form-data'로 지정해야 한다
  	
   -->
  
<h3> <%=name %>의 Request 연습용 Form <%=30+50 %></h3>
<form action="http://localhost/webTest/requestTest01.do" method="get">
<table border ="1">
	<tr>
		<td>이름</td>
		<td><input type="text" size="10" name
		="username"></td>
	</tr>
	<tr>
		<td>직업</td>
		<td>
			<select name="job">
				<option value="학생">==학생==</option>
				<option value="회사원">==회사원==</option>
				<option value="전문직">==전문직==</option>
				<option value="무직">==무직==</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>취미</td>
		<td>
			<input type="checkbox" name="hobby" value="여행">여행
			<input type="checkbox" name="hobby" value="독서">독서
			<input type="checkbox" name="hobby" value="게임">게임
			<input type="checkbox" name="hobby" value="배드민턴">배드민턴
			<input type="checkbox" name="hobby" value="탁구">탁구
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input type="submit" value="전송">
			<input type="reset" value="취소">
		</td>
	</tr>

</table>

</form>
<body>


</body>
</html>