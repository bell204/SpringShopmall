<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<html>
<style>

@CHARSET "UTF-8";



.background{
	
	 width:100%;
	height:100%;
	  text-align:center;
	
	position:absolute;
	
}

.login-form{
	
	
	background-color: #ffffff;
   	opacity:0.95;
	width:17%;
	height:45%;
	border-radius:5px;
    margin-left:auto;
	margin-right:auto;
	margin-top:150px;
	padding-top:20px;
	
	
}

.first-wrap{

	
}


.logo{
	
	width:90%;
	height:20%;
	margin-left:auto;
	margin-right:auto;
	margin-top:9px;
	
	
}
body {overflow:hidden}


.social-login{
	
	background:#f3f3f3;
	color:black;
	font-size:8px;
	padding-top:9px;
	padding-bottom:9px;
	width:90%;
	height:100px;
	margin-top:5px;
	margin-left:15px;
	
	
	
}
.enterPhone{
	
	margin-top:20px;
	padding:10px;
	padding-right:80px;
	
	
}
.enterpassword{
	
	padding:10px;
	padding-right:80px;
	
}

.nextbtn{
	
	background:#3385ff;
	color:white;
	border:none;
	margin-top:10px;
	padding: 10px 113px;
	margin-bottom:5px;
	
	
}


.naver{
	margin-top:10px;
	display:block;
	float:left;
	width:30%;
	height:50%;
	
	margin-left:15px;
	
}
.facebook{
	
	margin-top:10px;
	display:block;
	float:left;
	width:30%;
	height:50%;
	
	
}
.check-phone, .check-pwd{
	
	color:white;
	margin-left:20px;
	width:80%;
	padding:10px 10px; 
	border:none;
	background:rgba(0,0,0,.3); 
	font-size:12px;
	margin-top:3px;
	border-radius:3px;
}




.google{
	
		margin-top:10px;
	display:block;;
	float:right;
	width:30%;
	height:50%;
	
	margin-right:10px;
	
	
}

</style>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta  charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<div class="background" >
	<img  id="background" src="resources/images/${ aName }" style="position:absolute; width:100%;height:100%; margin-left:-880px;">
	

	<div class="login-form" id="login-form">
			<div class="logo">
				<img  src="<%=request.getContextPath()%>/resources/images/logo.png" style="width:30%; height:auto; ">
			</div>
			
			
			<div class="first-wrap" id="first-wrap">
			<input class="enterPhone" id="id" placeholder="휴대폰 번호" maxlength="11" name="mId">
			
			<input type="password" class="enterpassword" id="password" placeholder="비밀 번호"  maxlength="20" name="mPwd">
			 
			<button class="nextbtn" id="check-btn" onclick="checknext();" type="button">다음</button>
			<div class="check-phone" id="check-phone">휴대폰 번호를 제대로 입력해주세요.</div>
			<div class="check-pwd" id="check-pwd">비밀번호는 3글자 이상입니다.</div>
			
			
			
			<div class="social-login">
				가지고 있는 계정으로 시작하기
				<br>
				<br>
				<div class="naver">
					<img src="<%=request.getContextPath()%>/resources/images/naver.png" style="width:75%; height:auto; border-radius:5px; cursor:pointer;">
				</div>
				<div class="google">
					<img src="<%=request.getContextPath()%>/resources/images/google.png" style="width:80%; height:auto; border-radius:5px; cursor:pointer;">
				 </div>
				<div class="facebook">
					<img src="<%=request.getContextPath()%>/resources/images/facebook.jpg" style="width:62%; height:auto; border-radius:5px;cursor:pointer;" >
				</div>
			
			</div> 
		</div>	
		
		</div>
	</div>
		<script>
			
		$(function(){
			var date=new Date();
			timer = setInterval( function () {
			
				
				$.ajax ({
					url : "changePic.lo",
					method:"post",
					success : function (data) { 
						console.log(data.aName);
						$("#background").attr("src","resources/images/"+data.aName).fadeOut(700).stop(true,true).fadeIn(1200);
							
					}
				});

			},10000);
			
		});
		
		

		 $("#check-phone").hide();
		$("#check-pwd").hide(); 
		
		function checknext(){
			$(".first-wrap").animate({left:'-150px;'})
			var check=  /^(01[016789]{1})([0-9]{3,4})([0-9]{4})$/;
			var id=$("#id").val();
			var pwd=$("#password").val();
			console.log(id);
			
			if(check.test(id)&& pwd.length>=3){
				
				$.ajax({
					
					url:"checkMember.lo",
					type:"post",
					data:{mId:id, mPwd:pwd},
					success:function(data){
						
						if(data.result !=0) alert("아이디/비밀번호를 다시 확인 해 주세요.")
						else href="sdsdsd"
					},
					error:function(data){
						
						alert("실패");
						
					}
					
				})
				
				
				
			}
			if(!check.test(id)){
					
				if(pwd.length>=3){
					
					$("#check-phone").show();
					$("#check-pwd").hide(); 
					$("#login-form").css("height","55%");
					
				}else{
					
					 $("#check-phone").show();
					$("#check-pwd").show(); 
					$("#login-form").css("height","58%");
				}
					
			}
			if(pwd.length<3){
					
					if(check.test(id)){
						
						$("#check-phone").hide();
						$("#check-pwd").show(); 
						$("#login-form").css("height","55%");
					}
					
			}
				
				
		
			
			
			
		}
		
		
		
		</script>
		
		
		
		

</body>
</html>