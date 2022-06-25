<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
String pageNum =(String)request.getAttribute("pageNum");
int result = (int)request.getAttribute("result"); //글개수
int pageCount = (int)request.getAttribute("pageCount");
int pageBlock = (int)request.getAttribute("pageBlock");
int startPage = (int)request.getAttribute("startPage");
int endPage = (int) request.getAttribute("endPage");


%>
		<!-- 게시판 -->
		<div class="py-5 text-center" style="">
    <div class="container">
      <div class="row" style="opacity: 0.6;">
        <div class="mx-auto col-lg-10 col-10"> 
        <article>
<h1>NOTICE</h1>
PHONEGGU를 이용해주셔서 감사합니다.

구매 전 꼭 참고하신 후 구매에 불편이 없으시길 바랍니다 :)
	<br><br>
	
<table id="notice" class="table table-striped">
<tr><th class="tno">No.</th>
    <th class="ttitle">Title</th>
    <th class="twrite">Writer</th>
    <th class="tdate">Date</th>
    <th class="tread">Read</th></tr>
	
	<c:forEach var="dto"  items="${boardList }">
		<tr>
		   <td>${dto.num }</td>
		   <td class="left">
		     
		   		<a href="./BoardContent.no?num=${dto.num }&pageNum=<%=pageNum%>">
		   			${dto.title }
		   		</a>
		   </td>
		   <td>${dto.name }</td>
		   <td>${dto.date }</td>
		   <td>${dto.readcount }</td>
		</tr>	
	</c:forEach>
	
	
</table>
<div id="table_search">
<form action="./BoardSearch.no" method="get">	
	<input type="hidden" name ="result" value="<%=result %>">
	<input type="hidden" name ="pageCount" value="<%=pageCount %>">
	<input type="hidden" name ="pageBlock" value="<%=pageBlock %>">
	<input type="hidden" name ="startPage" value="<%=startPage %>">
	<input type="hidden" name ="endPage" value="<%=endPage %>">
		<input type="text" name="search" class="input_box">
		<input type="submit" value="search" class="btn-primary">
	</form>	
	<!-- 검색창 동작  -->
	<hr>
	
	
	<%
	String id =(String)session.getAttribute("id");
	if (id == null || !id.equals("admin")) {
	%>
	<input type="hidden" value="글쓰기" class="btn btn-primary pull-right" onclick=" location.href='./BoardWrite.no'; ">
<%}else{ %>
	<input type="button" value="글쓰기" class="btn btn-primary pull-right" onclick=" location.href='./BoardWrite.no'; ">
<%} %>
</div>

<div class="clear"></div>	
	
	<div id="page_control">
    	<%
    	if(result != 0){
    		
		// 이전
   		if(startPage > pageBlock){
   		%>
   		   <a href="./BoardList.no?pageNum=<%=startPage-pageBlock%>">[이전]</a>
  		<%
    	}
    	
   		//  1 2 3 4 ... 10   11 12 13.... 20 
    	for(int i=startPage;i<=endPage;i++){
    		   %>
    		       <a href="./BoardList.no?pageNum=<%=i%>">[<%=i %>]</a>      		   
    		   <% 		
    	}
   		
    	// 다음
    	if(endPage < pageCount){
    		%>
    		    <a href="./BoardList.no?pageNum=<%=startPage+pageBlock%>">[다음]</a>
    		<%
    	}
    	
    	}
   		
    	%>
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