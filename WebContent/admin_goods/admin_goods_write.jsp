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
	<%
		//관리자만 페이지 볼 수 있게 추가
		String id = (String) session.getAttribute("id");
		if (id == null || !id.equals("admin")) {
			response.sendRedirect("./Main.mm");
		}
	%>
	<div class="py-5 text-center" style="">
		<div class="container">
			<div class="row" style="opacity: 0.5;">
				<div class="mx-auto col-lg-10 col-10">
					<article>
						<fieldset>
							<h3>상품등록</h3>
						<form action="./GoodsAddAction.ag" method="post" enctype="multipart/form-data">
								<table border="1" class="table table-striped">
									<tr>
										<td>카테고리</td>
										<td><select name="category">
										    <option value="">상품 카테고리를 선택하세요.</option>
												<option value="hard">하드케이스</option>
												<option value="jelly">젤리케이스</option>
												<option value="griptok">그립톡</option>
												<option value="airpod">에어팟</option>
										</select></td>
									</tr>
									<tr>
										<td>상품이름</td>
										<td><input type="text" name="name"></td>
									</tr>
									<tr>
										<td>판매가격</td>
										<td><input type="text" name="price"></td>
									</tr>
									<tr>
										<td>수량</td>
										<td><input type="text" name="amount"></td>
									</tr>
									<tr>
										<td>기종</td>
										<td><input type="text" name="size"></td>
									</tr>
									<tr>
										<td>메인이미지</td>
										<td><input type="file" name="file1"></td>
									</tr>
									<tr>
										<td>제품이미지1</td>
										<td><input type="file" name="file2"></td>
									</tr>
									<tr>
										<td>제품이미지2</td>
										<td><input type="file" name="file3"></td>
									</tr>
									<tr>
										<td>제품이미지3</td>
										<td><input type="file" name="file4"></td>
									</tr>
								</table>
								<div id="table_search">
	<input type="submit" value="상품등록하기" class="btn">
</div>
							</form>
						</fieldset>
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
