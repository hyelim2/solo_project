<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디 중복 체크</title>

<style type="text/css">
#wrap {
	width: 490px;
	text-align: center;
	margin: 0 auto 0 auto;
}

#chk {
	text-align: center;
}

#cancelBtn {
	visibility: visible;
}

#useBtn {
	visibility: hidden;
}
</style>

<script type="text/javascript">
    
   
        // 회원가입창의 아이디 입력란의 값을 가져온다.
        function pValue(){
            document.getElementById("userId").value = opener.document.is.id.value;
        }
        
        // 아이디 중복체크
        function idCheck(){
 
            var id = document.getElementById("userId").value;
            
     /*   alert("${result}");  */
            
            	 if (!id) {
                     alert("아이디를 입력하지 않았습니다.");
                     return false;
                 } 
                 else if((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){ 
                     alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
                     return false;
                 } 
               
            
       		if (${result} == true){
            	alert("중복된 아이디입니다!");
           		return;
               
            }else{
           		alert("사용 가능한 아이디입니다!");
           		document.getElementById("useBtn").style.visibility='visible';
           		return;
            }
            
            
           // else{
            //	alert('정보를 입력하세요!');
            	//return false;
            //} 
        }
        
 
        // 사용하기 클릭 시 부모창으로 값 전달 
        function sendCheckValue(){
            // 중복체크 결과인 idCheck 값을 전달한다.
            opener.document.is.idDuplication.value ="idCheck";
            // 회원가입 화면의 ID입력란에 값을 전달
            opener.document.is.id.value = document.getElementById("userId").value;
            
            if (opener != null) {
                opener.chkForm = null;
                self.close();
            }    
        }    
   </script>

</head>
<body onload="pValue()">
	<div id="wrap">
		<br> <b><font size="4" color="gray">아이디 중복체크</font></b>
		<hr size="1" width="460">
		<br>
		<div id="chk">
			<form id="checkForm">
				<input type="text" name="idinput" id="userId"> <input
					type="button" value="중복확인" onclick="idCheck()">
			</form>
			<div id="msg"></div>
			<br> <input id="cancelBtn" type="button" value="취소"
				onclick="window.close()"><br> 
				<input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()" >
		</div>
	</div>
</body>
</html>


