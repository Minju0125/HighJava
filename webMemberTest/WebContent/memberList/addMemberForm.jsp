<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<script>
	$(function(){
		$('#checkid').on('click', function(){
			inputId = $('#id').val().trim();
			
			if(inputId<1){
				alert('id를 입력하세요.');
				return;
			}
			
			$.ajax({
				url : "<%=request.getContextPath()%>/memberList/result.jsp",
				type : "get",
				data : {"id" : inputId},
				//응답
				dataType : "json",
				success : function(res){
					$('#spanid').html(res.sw).css('color','red');
				},
				error : function(xhr){
					alert('상태 : ' + xhr.status)
				}
			})
			
		})
		
		$('#save').on('click', function(){
			vid = $('#id').val();
			vpass = $('#pass').val();
			vpasspass = $('#passpass').val();
			vname = $('#name').val();
			vtel = $('#tel').val();
			vaddr = $('#addr').val();
			vimage = "12" //파일 몰랑
			
			$.ajax({
				
			})
			
			
		})
	})

</script>

</head>




<body>
	<h3>회원 정보 입력 폼</h3>
	<form>
	<table border="1">
		<tr>
			<td>회원ID</td><td><input type="text" id="id"><input type="button" id="checkid" value="중복확인"><span id="spanid"></span></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><input type="text" id="pass"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td><td><input type="text" id="passpass"></td>
		</tr>
		<tr>
			<td>회원이름</td><td><input type="text" id="name"></td>
		</tr>
		<tr>
			<td>전화번호</td><td><input type="text" id="tel"></td>
		</tr>
		<tr>
			<td>회원주소</td><td><input type="text" id="addr"></td>
		</tr>
		<tr>
			<td>프로필사진</td><td><input type="button" value="파일선택" id="image"></td>
		</tr>
		<tr>
			<td><input type="submit" value="저장" id="save"><input type="button" value="취소" id="cancel"><input type="button" value="회원목록" id="register"></td>
		</tr>
	
	</table>
	</form>	



</body>
</html>