<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">

</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
	<jsp:include page="../inc/top.jsp"/>	
<!-- 헤더들어가는 곳 -->
<div class="py-5 text-center" style="">
			<div class="container">
				<div class="row" >
					<div class="mx-auto col-lg-10 col-10">
		<!-- 게시판 -->
		<article>
			<h5><%=session.getAttribute("id")%>님의 장바구니
			</h5>
		<%-- 	<h1>${ sessionScope.id }님의장바구니</h1>
			<h1>${ id }님의장바구니</h1> --%>
		<table id="notice" class="table table-striped">
				<tr>
					<th class="tno">번호</th>
					<th class="ttitle">사진</th>
					<th class="ttitle">상품명</th>
					<th class="ttitle">사이즈</th>
					<th class="ttitle">구매 수량</th>
					<th class="ttitle">가격(개당)</th>
					<th class="ttitle">취소</th>
				</tr>

				<c:forEach var="i" begin="0" end="${basketList.size()-1 }" step="1">
				<c:set var="basket" value="${basketList[i] }"></c:set>
				<c:set var="goods" value="${goodsList[i] }"></c:set>
					<tr>
						<td>${basket.b_num }</td>
						<td><img src="./shopUpload/${goods.image.split(',')[0] }" width="50"></td>
						<td>${goods.name }</td>
						<td>${basket.b_g_size }</td>
						<td>${basket.b_g_amount }</td>
						<td><fmt:formatNumber value="${goods.price }"/> </td>
						  <td> <a href="./BasketDelete.ba?b_num=${basket.b_num }">취소</a> </td>
					</tr>

				</c:forEach>


			</table>


			<div id="table_search">
			<h6><a href="./OrderStart.or">[구매하기]</a></h6>
			<h6><a href="./GoodsList.go">[계속 쇼핑하기]</a></h6>
			

			</div>

			<div class="clear"></div>

			<div id="page_control"></div>



		</article>
		</div>
		</div>
		</div>
		</div>
		
		
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>