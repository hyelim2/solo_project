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

<%
// 로그인 처리 -> 로그인 x (로그인페이지 이동)
String id = (String) session.getAttribute("id");
if( id == null ){
	 response.sendRedirect("./MemberLogin.me");
}
%> 


<body>
<!-- 헤더파일 -->
 	<jsp:include page="../inc/top.jsp"></jsp:include>
 	<!-- 헤더파일 -->
      <div class="row">
        <div class="col-md-8 px-md-5">
        </div>
      </div>
   <div class="py-3">
    <div class="container">
      <div class="row">
      </div>
    </div>
  </div>
  <div class="py-5 text-center" style="">
    <div class="container">
      <div class="row" style="opacity: 0.5;">
        <div class="mx-auto col-lg-6 col-10">
        

<div id="shopCustomerWithdrawal" class="myPage wrapper">
    <div class="column-wrapper one-column">
        <div class="withdrawal field">
            <div class="header designSettingElement text-title">
                <h3 class="title">회원 탈퇴</h3>
            </div>
            <div class="content designSettingElement text-body">
                
                <div class="row">
                    <span class="title align-center">탈퇴가 완료된 계정은 다시 복구할 수 없습니다.<br>탈퇴하시겠습니까?</span>
                </div>
                <div class="btn-wrapper">
	<form action="./MemberDeleteAction.mm" method="post">
	<!-- input타입중 hidden은 화면에 있는 해당 input태그를 숨겨서 정보 전달   -->
		<input type="hidden" name="id" value="<%=id %>" readonly><br>
		비밀번호 : <input type="password" name="pw"><br>
		<hr>
		<input type="submit" class="designSettingElement button" value="탈퇴하기">
		<input type="button" class="designSettingElement button" value="뒤로가기" onclick="location.href='./Main.mm'">
	</form>
                
                
                   <!--  <button class="designSettingElement button" onclick="./MemberDeleteAction.mm">탈퇴하기 -->
                    <!-- </button> -->
                </div>
            </div>
        </div>
    </div>
</div>

            
        </div>
      </div>
    </div>
  </div>
  
    <!-- 푸터 파일 -->
<jsp:include page="../inc/bottom.jsp"></jsp:include>
  <!-- 푸터 파일 -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>