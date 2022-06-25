<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">

</head>
<body>

<!-- 헤더파일 -->
 	<jsp:include page="../inc/top.jsp"></jsp:include>
 	<!-- 헤더파일 -->

		<!-- 게시판 -->
	<div class="py-5 text-center" style="">
    <div class="container">
      <div class="row" style="opacity: 0.5;">
        <div class="mx-auto col-lg-6 col-10">
		<article>
			<h1>Q &amp; A</h1>
			<%
			//부모글 가져오기
			String pageNum = (String) request.getParameter("pageNum");
			int num = Integer.parseInt(request.getParameter("num"));
			
			%>
			<form action="./BoardReWriteAction.bo" method="post">

			<input type="hidden" name="num" value="${param.num }">
			<input type="hidden" name="re_ref" value="${param.re_ref }">
			<input type="hidden" name="re_lev" value="${param.re_lev }">
			<input type="hidden" name="re_seq" value="${param.re_seq }">
			<input type="text" name="num" value="${dto.num }">
			<table id="notice">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" size="50" name="name"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" size="50" name="pw"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" size="50" name="title" value="[답글]"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" cols="52" name="content"></textarea></td>
				</tr>
			</table>
			<hr>
			<div id="table_search">
				<button type="reset" class="btn btn-primary">초기화</button>
				<button type="submit" class="btn btn-primary">글 작성</button> 
				  <input type="button" value="목록" class="btn btn-primary" onclick="location.href='./BoardList.bo'">
			</div>
				</form>
			<div class="clear"></div>
			<div id="page_control">
			</div>
		</article>
		</div>
		</div>
		</div>
		</div>
		
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
</body>
</html>