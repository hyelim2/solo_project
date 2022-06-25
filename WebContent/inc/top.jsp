<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<nav class="navbar navbar-expand-md navbar-light">
		<!-- 메인 아이콘 -->
		<div class="container">
			<a class="navbar-brand text-primary" href="./Main.mm"> <img
				alt="배너"
				src="./images/smartphone_chat_message_mobile_phone_love_heart_icon_209859.png">
				<!-- <i class="fa d-inline fa-lg fa-stop-circle"></i> --> <b>PHONEGGU</b>
			</a>
			<button class="navbar-toggler navbar-toggler-right border-0"
				type="button" data-toggle="collapse" data-target="#navbar4">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbar4">
				<!-- 배너  -->
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="./BoardList.no">ABOUT</a></li>
					
					
					<%
					String id = (String) session.getAttribute("id");
					if (id == null || !id.equals("admin")) {
				%>
					<li class="nav-item"><a class="nav-link" href="./GoodsList.go">PRODUCT</a>
					<%
					} else {
				%>
					<li class="nav-item"><a class="nav-link" href="./GoodsList.ag">PRODUCT</a>
				<%
					}
				%>
					
					</li>
					<li class="nav-item"><a class="nav-link" href="./BoardList.bo">Q&amp;A</a></li>
					<li class="nav-link"><a class="nav-item"
						href="./MemberInfo.mm">MY PAGE</a></li>
				</ul>
				<%
					if (session.getAttribute("id") == null) {
				%>
				<a class="btn navbar-btn ml-md-2 btn-light" class="nav-link"
					href="./MemberLogin.mm" >LOGIN</a>
				<%
					} else {
				%>
				<input type="button" class="btn navbar-btn ml-md-2 btn-light"
					class="nav-link" value="LOGOUT" 
					onclick="location.href='./MemberLogout.mm'"/>
				<%
					}
				%>
			</div>
		</div>
	</nav>
</header>