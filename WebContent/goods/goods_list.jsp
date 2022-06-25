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
<div id="wrap">
<!-- 헤더들어가는 곳 -->
	<jsp:include page="../inc/top.jsp"/>	
<!-- 헤더들어가는 곳 -->

<%
	//reqeust 영역에 저장
	//request.setAttribute("goodsList", goodsList);
	List<GoodsDTO> goodsList =(List<GoodsDTO>)request.getAttribute("goodsList");
%>
	<div class="py-5 text-center" style="">
			<div class="container">
				<div class="row" >
					<div class="mx-auto col-lg-10 col-10">
						<!-- 게시판 -->
<article>
<table id="notice" class="table table-striped">
	<tr>
		<th class="tno" colspan="4">
		<a href="./GoodsList.go">전체</a>  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
		<a href="./GoodsList.go?item=best">인기상품</a>  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
		<a href="./GoodsList.go?item=hard">하드케이스</a>   &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
		<a href="./GoodsList.go?item=jelly">젤리케이스</a>  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
		<a href="./GoodsList.go?item=griptok">그립톡</a>  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
		<a href="./GoodsList.go?item=airpod">에어팟</a> &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
		</th>
    </tr>
    
    
    <c:set var="size" value="${goodsList.size() }"/>
    <%-- 크기 : ${size } --%>
    <c:set var="col" value="4"/>
    <c:set var="row" value="${(size/col + (size%col>0? 1:0)) }"/>
    <%-- row : ${row } --%>
    <c:set var="goodsCnt" value="0"/>
    
    <c:set var="list" value="${goodsList }"/>
    
    
	    <c:forEach begin="1" end="${row }" step="1">
	       <tr>
	           <c:forEach begin="1" end="${col }" step="1">
	            <c:if test="${size > goodsCnt}">
	              <td>
	                 <img src="./shopUpload/${list[goodsCnt].image.split(',')[0]}"
						         width="140" height="140"><br>
						     
						     <a href="./GoodsDetail.go?num=${list[goodsCnt].num }">${list[goodsCnt].name }</a><br>
						     ${list[goodsCnt].price }원<br>		
	              </td>
                </c:if>
	              <c:set var="goodsCnt" value="${goodsCnt1 }"/>
	              
	           </c:forEach>         
	       </tr>
	    </c:forEach>
  
</table>


<div id="table_search">
</div>

<div class="clear"></div>
	
	<div id="page_control"></div>
	
	
	
</article>
</div>
</div>
</div>
</div>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>
