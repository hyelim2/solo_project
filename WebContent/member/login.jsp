<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#login-btn').click(function() {

			var id = $('#id').val();
			var pw = $('#pw').val()

			if (id.length > 0 && pw.length > 0) {
				$.ajax({
					url : './MemberLoginAction.mm',
					type : 'post',
					data : {
						id : $('#id').val(),
						pw : $('#pw').val()
					},
					success : function(res) {
						console.log(res);
						if (res == 'fail') {
							alert('올바른 정보가 아닙니다.');
						} else if (res == 'ok') {
							alert('로그인 되었습니다!');
							location.href = './Main.mm';
						}
					},
					error : function(error) {
						console.log(error);
					}
				});
			} else {

				alert('정보를 입력하세요!')
			}

		});
	});
</script>
<!-- 카카오로그인 스크립트 수정 필요 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	window.Kakao.init('37e73fac165dda7520189dc2a4a9fda9');
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	//카카오로그인
	
        function kakaoLogin() {
            window.Kakao.Auth.login({
           //     scope: 'profile, account_email, gender, age_range, birthday', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                success: function(response) {
                    console.log(response) // 로그인 성공하면 받아오는 데이터
                    window.Kakao.API.request({ // 사용자 정보 가져오기 
                        url: '/v2/user/me',
                        success: (res) => {
                            const kakao_account = res.kakao_account;
                            console.log(kakao_account)
                            alert('로그인 되었습니다!');
                            location.href = './Main.mm';
                        }
                    });
                    // window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
                },
                fail: function(error) {
                    console.log(error);
                }
            });
        }
	/* //카카오로그아웃  
	function kakaoLogout() {
	    if (Kakao.Auth.getAccessToken()) {
	      Kakao.API.request({
	        url: '/v1/user/unlink',
	        success: function (response) {
	        	console.log(response)
	        },
	        fail: function (error) {
	          console.log(error)
	        },
	      })
	      Kakao.Auth.setAccessToken(undefined)
	    }
	  }   */
	</script>
</body>

</head>
<body>
	<!-- 헤더파일 -->
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<!-- 헤더파일 -->
	<div class="py-3">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center"></div>
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- 폼태그 사용 x  -->
					<!-- <form action="./Main.mm" name="lo" method="post" onsubmit="return check();"> -->
					<div style="display: felx; flex-direction: column;"></div>
					<div class="form-group">
						<label>id</label> <input id="id" class="form-control"
							placeholder="id" name="id">
					</div>
					<div class="form-group">
						<label>Password</label> <input id="pw" type="password"
							class="form-control" placeholder="Password" name="pw">
					</div>
					<button id="login-btn" type="submit" class="btn btn-primary">Login</button>
					<!-- </form> -->
					<br><br>
					<!-- kakao로그인 -->
					<div class="button-login" >
						 <a href="javascript:kakaoLogin();"><img src="./images/kakao_login_small.png" alt="카카오계정 로그인" style="height: 33px;" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="py-5" style="opacity: 0.5;">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="list-group">
						<li
							class=" border-0 list-group-item d-flex justify-content-between align-items-center">
							<a href="./MemberInsert.mm" class="text-muted">JOIN</a> <i
							class="fa fa-cog text-muted fa-lg"></i>
						</li>
					<li
							class=" border-0 list-group-item d-flex justify-content-between align-items-center"><a
							href="./MemberLogout.mm" class="text-muted"> LOG OUT</a> <i
							class="fa fa-cog text-muted fa-lg"></i></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="text-center py-5" style="">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<a href="#" class="text-muted"> <i
						class="fa fa-fw fa-facebook fa-2x mx-3"></i>
					</a> <a href="#" class="text-muted"> <i
						class="fa fa-fw fa-twitter fa-2x mx-3"></i>
					</a> <a href="#" class="text-muted"> <i
						class="fa fa-fw fa-2x fa-google-plus mx-3"></i>
					</a> <a href="#" class="text-muted"> <i
						class="fa fa-fw fa-instagram fa-2x mx-3"></i>
					</a>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous" style=""></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous" style=""></script>
</body>

</html>