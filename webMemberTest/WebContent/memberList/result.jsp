<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String res = (String)request.getAttribute("id");

if(res==null){
%>
	{
		"sw" : "사용가능"
	}
<%
}else{
	 %>

	{
		"sw" : "사용불가능"	
		
	}
<%
}
 %>