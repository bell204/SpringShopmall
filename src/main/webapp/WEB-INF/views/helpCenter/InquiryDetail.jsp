<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html dir="ltr" lang="ko">
<head>

<style>
#retrunList {
	border-radius: 4px;
	box-sizing: border-box;
	color: #999;
	height: 30px;
	margin-top: 30px;
	padding-left: 80px;
	-webkit-appearance: none;
	width: 25%;
	float: right;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.8); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 30%; /* Could be more or less, depending on screen size */
}
a{
	border-radius: 4px;
	box-sizing: border-box;
	color: #999;
	height: 30px;
}
</style>
<body class="">

	<c:if test="${! empty notice }">

	</c:if>


	<jsp:include page="../common/header.jsp" />

	<main class="main-content slide-left" id="main-content" role="main">
	<article class="post" id="main">
		<header class="post-header">

			<c:forEach var="Notice" items="${ list }">

				<div class="container">

					<div class="post-badges">${ Notice.NOTICE_NO }번글</div>
					<h1 class="post-title">${ Notice.NOTICE_TITLE }</h1>

					<div class="post-meta btn-toolbar">
						<div class="btn-toolbar media-body">

							<div class="media-body">
								<time class="list-inline-item" align="right">${ Notice.NOTICE_DATE }</time>
							</div>
						</div>
					</div>
					
					<!-- 수정 삭제 -->
					<div>
					<p align="right"><a  href="#">수정</a> &nbsp;
					<a align="right" href="#">삭제</a>
					</p>
					</div>
					
				</div>
		</header>


		<div class="container"></div>


	</article>

	<br>
	<br>

	<section class="responses comments" id="responses">
		<div class="container">
			<div class="comments" id="comments">
				<textarea class="form-control autosize" name="content" id="mention"
					rows="20">
					${ Notice.NOTICE_CONTENT }
					</textarea>

			</div>
		</div>
	</section>
	<script>
	function pwd(){
		 var NOTICE_PWD=$("#NOTICE_PWD").val();
		 var NOTICE_NO=${ Notice.NOTICE_NO };
	 if(NOTICE_PWD==""){
		 alert("암호를 입력해주세요")
		 return false;
	 }
		
		$.ajax({
			url:"comparePassword.help",
			type:"post",
			dataType:"json",
			data:{"NOTICE_PWD": NOTICE_PWD,
				"NOTICE_NO":NOTICE_NO},
			success:function(data){
				if(!data==""){
					alert("암호가 맞습니다. 창을 닫아주세요")
					$("#myClose").show();
				};
			}
			
			});
		return false;
	}
	
	</script>
	
	</c:forEach> <!--  본문 끝 --> <br>
	<br>

	<section class="responses comments" id="responses">
		<div class="container">

			<div class="comments" id="comments">
				<h3 class="section-title">관리자 답변</h3>

				<form>
					<textarea class="form-control autosize" name="ReplyHelp"
						id="ReplyHelp"></textarea>


					<div class="clearfix">

						<div class="float-right">
							<button class="btn btn-create-comment" onclick="return Reply()">관리자 답변</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>


	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<p style="text-align: center;">
				<span style="font-size: 14pt;"><b><span
						style="font-size: 24pt;">비밀글입니다.</span></b></span>
			</p>
			<p style="text-align: center; line-height: 1.5;">
				<br />
			<p align="center">문의 등록 시 입력하신 비밀번호를 입력해 주세요.</p>
			<p align="center">비밀번호<input type="password" name="NOTICE_PWD" id="NOTICE_PWD"> 
			<a onclick="pwd()"class="signup-form__submit ga-event">확인</a>
			</p>
			<p>
				<br />
			</p>
			<div id="myClose"
				style="cursor: pointer; display: none; background-color: #DDDDDD; text-align: center; padding-bottom: 10px; padding-top: 10px;"
				onClick="close_pop();">
				<span class="pop_bt" style="font-size: 13pt;"> 닫기 </span>
			</div>
		</div>

	</div>
	<!--End Modal--> <script type="text/javascript">
      
        jQuery(document).ready(function() {
                $('.modal').show();
                 
        });
        //팝업 Close 기능
          function close_pop(flag) {
                    $('.modal').hide();
                    
               };
                
    </script> <!--  댓글 표시 영역 -->
	<table class="" id="Reply">
		<tr>
		</tr>

	</table>


	<script>
	function Reply(){
		var ReplyHelp= $("#ReplyHelp").val();
		
		$.ajax({
			url:"Reply.help",
			type:"post",
			dataType:"json",
			data:{ReplyHelp, ReplyHelp},
			success:function(data){
  
				var $table = $("#Reply").css("width","300px");
				$table.html('');
				
				var $tr = $("<tr>");
				//var $th = $("<td>").text("내용").css('float','center');
				
				//var $total=$table.append($tr).append($th);
				
				for(var i=0; i<data.length; i++){
				 
					var $contentTd = $("<td>").text(data[i].comment_content).css({"width":"100%","background":"orangered"});
					var $tr1=$tr.append($contentTd);
			
					$table.append($tr1);
				}
			}
		});
		return false;
	}
 
	 
	 </script> <a href="InquiryList.help" id="retrunList">목록으로 돌아가기</a> </main>



	<jsp:include page="../common/footer.jsp" />

</body>
</html>