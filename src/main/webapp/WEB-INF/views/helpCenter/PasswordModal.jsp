<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>
   
<div id="status-bar"></div>
  
<div id="wrapper">
    <div class="signup-form">
   
    <div class="signup-form__logo-box">
    <form id="FAQpwd">
       <h1>비밀글입니다.</h1>
       <p>아래 빈칸에 문의 글 등록 시 입력하신 비밀번호를 입력해 주세요.</p>
           <label>비밀번호</label>
    <input type="password" name="NOTICE_PWD" id="NOTICE_PWD">
    
    <div class="signup-form__catchphrase">
   
    </div>
    </div>
    <div>
    <a onclick="pwd()" class="signup-form__submit ga-event">확인</a>
     </form>
  
    <a href="InquiryList.help" class="signup-form__submit--login">목록</a></div> 
 
 <script>
 function pwd(){
	 
	 var NOTICE_PWD=$("#NOTICE_PWD").val();
	 
	 if(NOTICE_PWD==""){
		 alert("암호를 입력해주세요")
		 return false;
	 }
		
		$.ajax({
			url:"InquiryDetail.help",
			type:"post",
			dataType:"json",
			data:{NOTICE_PWD, NOTICE_PWD},
			success:function(data){
			
			alert(data);
			}
			
			});
		return false;
	}
 
 </script>
 
</body>
</html>