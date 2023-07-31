<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){ //현재 페이지를 웹브라우저가 읽어서 DOM 객체로 구성한 후 실행되는 영역
	$("#strBtn").on('click', function(){
		$.ajax({ 
			//---서버로 요청할 때 필요한 정보
			url : "<%=request.getContextPath()%>/jsonController.do", //요청할 주소
			type : "post",
			data : "choice=str", //서버로 보낼 데이터 (url에 지정한 주소로 요청을 하면, 서블릿으로 가는데 거기로 보낼 데이터)

			//-- 응답으로 올 때 지정할 정보
			success : function(data){ 
				// jsonController.do여기로 데이터 보내서 요청을 하면, 거기서 처리를 해서 보내줌. 
				// -> 그 응답이 정상적으로 왔을 때 실행 | data 변수 ==> 응답으로 온 데이터가 저장된다.(서블릿으로 처리하고 응답으로 데이터가 보내왔을 때 여기에 저장됨)
				$("#result").html(data);
			},
			error : function(){
				alert("오류...")
			},
			dataType : "json" //응답으로 온 데이터 타입 지정 //success 를 통해 저장되는 데이터 타입
			
		})
	});
	
	$("#arrayBtn").on('click', function(){
		$.ajax({ 
			//---서버로 요청할 때 필요한 정보
			url : "<%=request.getContextPath()%>/jsonController.do", //요청할 주소
			type : "post",
			data : "choice=array", //서버로 보낼 데이터 (url에 지정한 주소로 요청을 하면, 서블릿으로 가는데 거기로 보낼 데이터)

			success : function(data){  //data 변수 ==> 응답으로 온 데이터가 자동으로 저장된다.
				let htmlCode = "";
				$.each(data, function(i,v){ // i: 인덱스, v: 인덱스번째 데이터
					htmlCode += i+ "번째 값 : " + v + "<br>";
				});
				
				$("#result").html(htmlCode);
				
			
			},
			error : function(){
				alert("오류...")
			},
			dataType : "json" //응답으로 온 데이터 타입 지정 //success 를 통해 저장되는 데이터 타입
			
		})
	});
	
	//객체 처리
	$("#objBtn").on("click", function(){
		$.ajax({ 
			//---서버로 요청할 때 필요한 정보
			url : "<%=request.getContextPath()%>/jsonController.do", //요청할 주소
			type : "post",
			data : "choice=object", //서버로 보낼 데이터 (url에 지정한 주소로 요청을 하면, 서블릿으로 가는데 거기로 보낼 데이터)

			//-- 응답으로 올 때 지정할 정보
			success : function(data){ 
				//data ==> {"num" : 10, "name" : 홍길동}
				let htmlCode = "번호 : " + data.num + "<br>";
				htmlCode += "이름 : " + data.name + "<br>";
				
				$("#result").html(htmlCode);
			},
			error : function(){
				alert("오류...")
			},
			dataType : "json" //응답으로 온 데이터 타입 지정 //success 를 통해 저장되는 데이터 타입
			
		})
	})
});
</script>
</head>
<body>

<form>
	<input type="button" id="strBtn" value="문자열">
	<input type="button" id="arrayBtn" value="배열">
	<input type="button" id="objBtn" value="객  체">
	<input type="button" id="listBtn" value="리스트">
	<input type="button" id="mapBtn" value="Map 객체">
</form>
<h3>응답 결과</h3>
<div id="result"></div>

</body>
</html>