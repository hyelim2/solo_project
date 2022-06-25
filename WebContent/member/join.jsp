<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
  <script type="text/javascript">
  
  
			function check() {
				// 아이디 입력여부
				if (document.is.id.value.length < 1) {
					alert(" 아이디를 입력하세요! ");
					document.is.id.focus();
					return false;
				}
				//로그인 중복 체크
				if (document.is.idDuplication.value != "idUncheck") {
					alert("아이디 중복 체크를 해 주세요!");
					return false;
				}
				// 비밀번호   "
				if (document.is.pw.value == "") {
					alert(" 비밀번호를 입력하세요! ");
					document.is.id.focus();
					return false;
				}
				var docIs = document.is;
				// 이름   "
				if (docIs.name.value == "") {
					alert(" 성함을 입력하세요! ");
					document.is.id.focus();
					return false;
				}
				//연락처
				if (docIs.phone.value == "") {
					alert(" 연락처를 입력하세요! ");
					document.is.id.focus();
					return false;
				}
				// 이메일     "
				if (docIs.email.value.length < 1) {
					alert(" 이메일을 입력하세요! ");
					document.is.id.focus();
					return false;
				}

			}

			//아이디 중복 체크 화면 열기
			function openIdChk() {
				//window.name = "parentForm";
				window.open("./MemberIdCheck.mm?id="+document.is.id.value, "chkForm",
								"width=500, height=300, resizable = no, scrollbars = no"); 
			}
			// 아이디 입력창에 값 입력시 hidden에 idUncheck 세팅
			//중복 체크 후에 새로운 아이디 창이 새로운 아이디를 입력했을 때 다시 중복 체크 하도록!
			function inputIdChk() {
				document.is.idDuplication.value = "idUncheck";
			}
		</script>
</head>
<body>
<!-- 헤더파일 -->
 	<jsp:include page="../inc/top.jsp"></jsp:include>
 	<!-- 헤더파일 -->
      <div class="row">
        <div class="col-md-8 px-md-5">
        </div>
      </div>
  <div class="py-5 text-center" style="">
    <div class="container">
      <div class="row" style="opacity: 0.5;">
        <div class="mx-auto col-lg-6 col-10">
          <h1>JOIN</h1>
          <p class="mb-3">When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees.</p>
         
         <form class="text-left" action="./MemberInsertAction.mm" name="is" method="post" 
          onkeydown="inputIdChk()" onsubmit="return check()">
            <div class="form-group col-md-12"><label for="form16">ID</label> <input type="text" class="form-control" id="form16" placeholder="ID" name="id"><br> 
             <!-- 아이디 중복 체크 여부 -->
            <button type="button" onclick="openIdChk()" name="checkID" class="btn btn-primary">중복확인</button>
			<input type="hidden" name="idDuplication" value="idUncheck"/></div>
			<!-- 비밀번호, 이름, 이메일, 연락처  -->
            <div class="form-group col-md-12"> <label for="form20"> Password</label> <input type="password" class="form-control" id="form19" placeholder="••••" name="pw"> </div>
            <div class="form-group"> <label for="form16">Your Name</label> <input type="text" class="form-control" id="form16" placeholder="Your Name " name="name"> </div>
            <div class="form-group"> <label for="form17">Your Phone Number</label> <input type="text" class="form-control" id="form17" placeholder="Your Phone Number" name="phone"> </div>
            <div class="form-group"> <label for="form18">Your email</label> <input type="email" class="form-control" id="form18" placeholder="AAA@case.com" name="email"> </div>
            <div class="form-row">
            </div>
            <div class="form-group">
            </div> <button type="submit" class="btn btn-primary">Sign in</button> 
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>
</html>