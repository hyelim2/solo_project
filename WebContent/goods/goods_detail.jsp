<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	<script type="text/javascript">
    
   function isBasket(){
	  // alert('test!!!');
	  // 데이터 유효성 체크 (구매수량,옵션)
	  
	  // 최소 수량 체크
	  if(document.gfr.amount.value < 1){
		  alert("최소 구매 수량은 1개 입니다.");
		  document.gfr.amount.focus();
		  document.gfr.amount.value = 1;
		  return;
	  }
	  
	  // 최대 수량 체크
	  if(document.gfr.amount.value > ${dto.amount}){
		  alert("최대 구매 수량은 ${dto.amount}개 입니다.");
		  document.gfr.amount.select();
		  return;
	  }
	  
	  // 사이즈
	  if(document.gfr.size.value == ""){ // 옵션선택 X
		  alert(" 기종을 선택하세요. ");
	      document.gfr.size.focus();
	      return;
	  }
	  
	  ////////////////////////////////////////////////////
	  // 옵션 선택완료
	  var result = confirm("장바구니로 이동하겠습니까?");
	  
	  if(result){ //result == true 
		  // 장바구니 페이지 이동 (submit)
	     document.gfr.action="./BasketAdd.ba";
		 document.gfr.submit();		  
		  
	  }else{
		  //  ajax 사용해서 정보만 저장
		  return;		  
	  }
   }
   
</script>
</head>
<body>
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더들어가는 곳 -->


		<%
			
		%>
		<div class="py-5 text-center" style="">
			<div class="container">
				<div class="row">
					<div class="mx-auto col-lg-10 col-10">
						<!-- 게시판 -->
						<article>
							<!-- <h1>등록 상품 상세페이지(사용자용)</h1> -->

							<form action="" method="post" name="gfr">
								<input type="hidden" name="num" value="${dto.num }">
								<table id="notice">
									<tr>
										<td><img src="./shopUpload/${dto.image.split(',')[0] }"
											width="300" height="300"></td>
										<td>
											<h4>상품명 : ${dto.name }</h4>
											<h4>가격 : ${dto.price }원</h4>
											<h5>
												구매수량 : <input type="number" name="amount">개
											</h5>
											<h4>남은수량 : ${dto.amount }개</h4> 기종 : <select name="size">
												<option value="">기종을 선택하시오</option>
												<c:forTokens var="size" items="${dto.size }" delims=",">
													<option value="${size }">${size }</option>
												</c:forTokens>
										</select> <br> <a href="javascript:isBasket()">[장바구니 담기]</a><br>
											<a href="#">[바로 구매하기]</a><br>

										</td>
									</tr>
									<tr>
										<td colspan="2"><img
											src="./shopUpload/${dto.image.split(',')[1] }" width="600"
											height="800"> <img
											src="./shopUpload/${dto.image.split(',')[2] }" width="600"
											height="800"> <img
											src="./shopUpload/${dto.image.split(',')[3] }" width="600"
											height="800"></td>
									</tr>
								</table>
							</form>

							<div id="table_search"></div>

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