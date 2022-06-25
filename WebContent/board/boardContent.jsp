<%@page import="hl_project.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

	<!-- 헤더파일 -->
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<!-- 헤더파일 -->
	<div class="mx-auto col-lg-6 col-10">
		<!-- 게시판 글 보기 양식 영역 시작 -->
		<div class="container">
			<div class="row">
				<%
					//request 영역에 글정보를 저장
					//request.setAttribute("dto", dto)
					BoardDTO dto = (BoardDTO) request.getAttribute("dto");
					String pageNum = (String) request.getAttribute("pageNum");
				%>


				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="4"
								style="background-color: #eeeeee; text-align: center;">Q &
								A</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%;">번호</td>
							<td colspan="2"><%=dto.getNum()%></td>
						</tr>
						<tr>
							<td>제목</td>
							<td colspan="2"><%=dto.getTitle()%></td>
						</tr>
						<tr>
							<td>작성자
							<td colspan="2"><%=dto.getName()%></td>
						</tr>
						<tr>
							<td>조회수</td>
							<td colspan="2"><%=dto.getReadcount()%></td>
						</tr>
						<tr>
							<td>작성일자</td>
							<td><fmt:formatDate value="${dto.date }" pattern="yy-MM-dd" />
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="2" style="height: 200px; text-align: left;"><%=dto.getContent()%></td>
						</tr>
						<tr>
							<td>첨부파일</td>
							<td colspan="4"><a href="./upload/${dto.file}">${dto.file }
									<hr> <a href="./board/fileDown.jsp?fileName=${dto.file }">${dto.file }</a></td>
						</tr>
					</tbody>
				</table>
				<input type="button" value="목록" class="btn btn-primary"
					onclick="location.href='./BoardList.bo?pageNum=<%=pageNum%>';">
				<%
					String id = (String) request.getAttribute("id");
					if (session.getAttribute("id") != null) {
				%>
				<input type="button" value="답글" class="btn btn-primary"
					onclick="location.href='./BoardReWrite.bo?num=<%=dto.getNum()%>&re_ref=<%=dto.getRe_ref()%>&re_lev=<%=dto.getRe_lev()%>&re_seq=<%=dto.getRe_seq()%>';">
				<%
					}
				%>
				<!-- 해당 글의 작성자가 본인이라면 수정과 삭제가 가능하도록 코드 추가 -->

				<input type="button" value="수정" class="btn btn-primary" id="upBtn"
					onclick="location.href='./BoardUpdate.bo?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>'">
				<input type="button" value="삭제" class="btn btn-primary" id="delBtn"
					onclick="location.href='./BoardDelete.bo?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>'">
			</div>
		</div>
		<!-- 게시판 글 보기 양식 영역 끝 -->


	</div>
	<!-- 게시판 글 보기 양식 영역 끝 -->
	<!-- 본문들어가는 곳 -->
	<div class="clear"></div>
	<!-- 푸터들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp"></jsp:include>
</body>
</html>