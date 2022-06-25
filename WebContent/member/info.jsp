<%@page import="hl_project.member.db.MemberDTO"%>
<%@page import="hl_project.member.db.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">

</head>
<body>

	<%
		//세션 영역에 저장된 id 정보 
		String id = (String) session.getAttribute("id");

		if (id == null) {
			response.sendRedirect("./MemberLogin.mm");
		}	
	%>
	<!-- 헤더파일 -->
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<!-- 헤더파일 -->
	<div class="row">
		<div class="col-md-8 px-md-5"></div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row"></div>
		</div>
	</div>
	<div class="py-1 text-center">
		<div class="container">
			<div class="row" style="opacity: 0.5;">
				<div class="mx-auto col-lg-6 col-10">
					<ul class="list-group">
						<h3><%=id%>님 반갑습니다!</h3>
						<tr>
							<td>
								<div class="form-group col-md-12">
									 <label for="form16">ID</label> <br>
									<%--  <label for="form20">${dto.id }</label> --%>
									 <input type="text"
										class="form-control" id="form16" name="id" value="${dto.id }"> 
								</div> <!-- 비밀번호, 이름, 이메일, 연락처  -->
								<div class="form-group col-md-12">
									<label for="form20"> Password</label> <input type="password"
										class="form-control" id="form19" placeholder="비밀번호를 입력하세요" name="pw">
								</div>
								<div class="form-group">
									<label for="form16">Your Name</label> <input type="text"
										class="form-control" id="form16" value="${dto.name }"
										name="name">
								</div>
								<div class="form-group">
									<label for="form17">Your Phone Number</label> <input
										type="text" class="form-control" id="form17" value="${dto.phone }"
										 name="phone">
								</div>
								<div class="form-group">
									<label for="form18">Your email</label> <input type="email"
										class="form-control" id="form18" value="${dto.email }"
										name="email">
								</div>
								<div class="form-row"></div>
							</td>
						</tr>




						<li
							class=" border-0 list-group-item d-flex justify-content-between align-items-center"><a href ="./MemberUpdate.mm">
							UPDATE</a> <i class="fa fa-pie-chart text-muted fa-lg"></i>
						</li>
						<li
							class=" border-0 list-group-item d-flex justify-content-between align-items-center"><a href ="./MemberDelete.mm">
							DELETE</a><i class="fa fa-sign-out text-muted fa-lg"></i>

						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- 푸터 파일 -->
	<jsp:include page="../inc/bottom.jsp"></jsp:include>
	<!-- 푸터 파일 -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>

</html>