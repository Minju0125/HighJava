<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/vendors/feather/feather.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/vertical-layout-light/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/images/favicon.png" />
</head>

<body>
	<div class="container-scroller">
		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
				<a class="navbar-brand brand-logo mr-5" href="">
					<img src="${pageContext.request.contextPath }/resources/images/logo.svg" class="mr-2" alt="logo" />
				</a> 
				<a class="navbar-brand brand-logo-mini" href="">
					<img src="${pageContext.request.contextPath }/resources/images/logo-mini.svg" alt="logo" />
				</a>
			</div>
			<div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
				<button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
					<span class="icon-menu"></span>
				</button>
				<ul class="navbar-nav navbar-nav-right">
					
				</ul>
				<button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
					<span class="icon-menu"></span>
				</button>
			</div>
		</nav>
		<div class="container-fluid page-body-wrapper">
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">
					<li class="nav-item">
						<a class="nav-link" href=""> 
							<i class="icon-grid menu-icon"></i> 
							<span class="menu-title">공지사항</span>
						</a>
					</li>
				</ul>
			</nav>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">공지사항 목록</h4>
									<p class="card-description">
										대덕인재개발원 종합 테스트
										<code>게시판 목록 페이지를 작성합니다.</code>
									</p>
									<div class="table-responsive">
										<table class="table table-hover">
											<thead class="table-dark">
												<tr>
													<th>번호</th>
													<th>제목</th>
													<th>작성자</th>
													<th>작성일</th>
													<th>조회수</th>
												</tr>
											</thead>
											<tbody>
												<%
													List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");
													if(boardList == null || boardList.size() ==0){
														
													
												%>
													<tr>
														<td colspan="5">
															조회하실 게시글이 존재하지 않습니다.
														</td>
													</tr>
													<%
													}else{
														for(int i=0; i <boardList.size(); i++){
															BoardVO board = boardList.get(i);
													%>
													<tr>
														<td><%=board.getBo_no() %></td>
														<td>
															<a href="${pageContext.request.contextPath }/board/view.do?bono=<%=board.getBo_no()%>">
																	<%=board.getBo_title() %>
															</a>
																</td>
														<td><%=board.getBo_writer()%></td>
														<td><%=board.getBo_date() %></td>
														<td><%=board.getBo_hit() %></td>
													</tr>
												
													<%
															
														}
													}
																									
													%>
												
												
												
												
												
												
											</tbody>
										</table>
									</div>
									<button type="button" class="btn btn-outline-primary btn-fw" id="addBtn">등록</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<footer class="footer">
					<div class="d-sm-flex justify-content-center justify-content-sm-between">
						<span class="text-muted text-center text-sm-left d-block d-sm-inline-block">
							Copyright © 2023. DDIT All rights reserved.
						</span>
					</div>
				</footer>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath }/resources/vendors/js/vendor.bundle.base.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/template.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</body>
<script type="text/javascript">
$(function(){
	var addBtn = $("#addBtn"); // 등록버튼 Element  얻어옴
	
	//등록 폼 페이지로 이동
	addBtn.on('click', function(){
		location.href="/board/form.do";
	});
})

</script>

</html>