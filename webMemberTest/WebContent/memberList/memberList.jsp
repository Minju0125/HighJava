<%@page import="kr.or.ddit.memberList.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<script>

	$(function(){
		$('#buttonToAddMemberForm').on('click', function(){
			location.href = "<%=request.getContextPath()%>/memberList/addMemberForm.jsp"
		})
	})
</script>

<%
List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList");
%>
<body>
	



	<h3>회원 목록 보기</h3>
	<table border="1">
		<tr>
			<td><input type="button" id="buttonToAddMemberForm" value= "회원추가"></td>
		</tr>
		<tr><th>ID</th><th>비밀번호</th><th>이름</th><th>전화</th><th>주소</th></tr>

<%
for(MemberVO memvo : memberList){

%>		

<tr>
	<td><%=memvo.getMem_id() %></td>
	<td><%=memvo.getMem_pass() %></td>
	<td><%=memvo.getMem_name() %></td>
	<td><%=memvo.getMem_tel() %></td>
	<td><%=memvo.getMem_addr() %></td>
</tr>

<%
}
%>
	
	</table>


</body>
