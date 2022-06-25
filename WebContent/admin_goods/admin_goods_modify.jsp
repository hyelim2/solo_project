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
			response.sendRedirect("./Main.mm");
		}
		
		GoodsDTO dto = (GoodsDTO) request.getAttribute("dto");
	%>
	<div class="py-5 text-center" style="">
		<div class="container">
			<div class="row" style="opacity: 0.5;">
				<div class="mx-auto col-lg-10 col-10">
				<article>
<form action="./GoodsModifyProAction.ag" method="post">
<input type ="hidden" name="num" value="${dto.num }">

<table id="notice" class="table table-striped">
	<tr>
    	<th class="ttitle" colspan="5"> 관리자 상품수정 </th>
	</tr>
	<tr>
		<td>상품카테고리</td>
	    <td>
	       <select name="category">
	          <option value="">상품 카테고리를 선택하세요.</option>
	          <option value="hard"
	          <c:if test="${dto.category == 'hard' }">
	          selected="selected"
	          </c:if>
	          >하드케이스</option>
	          <option value="jelly"
	             <c:if test="${dto.category == 'jelly' }">
	          selected="selected"
	          </c:if>
	          >젤리케이스</option>
	          <option value="griptok"
	             <c:if test="${dto.category == 'griptok' }">
	          selected="selected"
	          </c:if>
	          >그립톡</option>
	          <option value="airpod"
	             <c:if test="${dto.category == 'airpod' }">
	          selected="selected"
	          </c:if>
	          >에어팟</option>
	       </select>
	    </td>
    </tr>
    <tr>
      <td>상품이름</td>
      <td><input type="text" name="name" value="${requestScope.dto.name }"></td>
    </tr>
    <tr>
      <td>판매가</td>
      <td><input type="number" name="price" value="${dto.price }"></td>
    </tr>
    <tr>
      <td>수량</td>
      <td><input type="number" name="amount" value="${dto.amount }"></td>
    </tr>
    <tr>
      <td>사이즈</td>
      <td><input type="text" name="size" value="${dto.size }"></td>
    </tr>
    <tr>
      <td>제품정보</td>
      <td><input type="text" name="content" value="${dto.content }"></td>
    </tr>
    <tr>
      <td>인기상품</td>
      <td>
      <input type="radio" name="best" value="1"
      <c:if test="${dto.best ==1}">
      checked="checked"
      </c:if>
      > 예 (인기상품등록)
      <input type="radio" name="best" value="0"
          <c:if test="${dto.best ==0}">
      checked="checked"
      </c:if>
      > 아니오 (일반상품등록)
      </td>
    </tr>
  
</table>

<div id="table_search">
	<input type="submit" value="상품 수정하기" class="btn">
</div>

</form>
<div class="clear"></div>
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