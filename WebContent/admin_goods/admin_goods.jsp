<%@page import="hl_project.admin.goods.db.GoodsDTO"%>
<%@page import="hl_project.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>   
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
	<%
		//관리자만 페이지 볼 수 있게 추가
		String id = (String) session.getAttribute("id");
		if (id == null || !id.equals("admin")) {
			response.sendRedirect("./GoodsList.go");
		}
		List<GoodsDTO> goodsList =(List<GoodsDTO>)request.getAttribute("goodsList");
	%>
	<div class="py-5 text-center" style="">
		<div class="container">
			<div class="row" style="opacity: 0.6;">
				<div class="mx-auto col-lg-10 col-10">
				<!-- 게시판 -->
<article>
<h1>등록 상품 목록(관리자용)</h1>
<table id="notice" class="table table-striped">
	<tr>
		<th class="ttitle" colspan="2">
		  <a href="./GoodsAdd.ag">상품<br>등록하기</a>
		  <a class="nav-link" href="./GoodsList.go">user용</a>
		</th>
    </tr>
	<tr>
		<th class="tno">번호</th>
		<th class="ttitle">사진</th>
	    <th class="ttitle">상품명</th>
	    <th class="ttitle">카테<br>고리</th>
	    <th class="ttitle">가격</th>
	    <th class="ttitle">수량</th>
	    <th class="ttitle">등록일</th>
	    <th class="ttitle">수정<br>삭제</th>
    </tr>
    
    <%for(int i=0;i<goodsList.size(); i++){
    GoodsDTO dto = goodsList.get(i);
    %>
	    <tr>
			<td><%=dto.getNum() %></td>
			<td>
				<img src="./shopUpload/<%=dto.getImage().split(",")[0]%>"
				width="50" height="50">
			</td>
		    <td><%=dto.getName() %></td>
		    <td><%=dto.getCategory() %></td>
		    <td><%=dto.getPrice() %></td>
		    <td><%=dto.getAmount() %></td>
		    <td><%=dto.getDate() %></td>
		    <td>
		    <a href="./GoodsModify.ag?num=<%=dto.getNum()%>">수정</a>
		    <br>
		   <a href="./GoodsDeleteAction.ag?num=<%=dto.getNum() %>" >삭제</a></td>
	    </tr>
    <%} %>
    
</table>


<div id="table_search">
	<hr>
</div>

<div class="clear"></div>
	
	<div id="page_control"></div>
	
	
	
</article>
					
				</div>
			</div>
		</div>
	</div>

	<div class="clear"></div>
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp"></jsp:include>
	<!-- 푸터 들어가는 곳 -->
</body>
</html>