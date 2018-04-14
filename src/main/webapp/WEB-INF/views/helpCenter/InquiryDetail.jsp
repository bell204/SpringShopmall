<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html dir="ltr" lang="ko">
<head>

<style>

#retrunList  {
 border-radius: 4px;
  box-sizing: border-box;
  color: #999;
  height: 30px;
  margin-top: 30px;
  padding-left: 80px;
  -webkit-appearance: none;
  width: 25%;
  float:right;  
}

</style>

<body class="">

	<c:if test="${! empty notice }">

		<c:out value="${notice.NOTICE_CONTENT }" />
	</c:if>
     

	<jsp:include page="../common/header.jsp" />

	<main class="main-content slide-left" id="main-content" role="main">
	<article class="post" id="main">
		<header class="post-header">
			
			<c:forEach var="Notice" items="${ list }">
        
			<div class="container">

				<div class="post-badges">${ Notice.NOTICE_NO }번글 </div>
				<h1 class="post-title">${ Notice.NOTICE_TITLE }</h1>

				<div class="post-meta btn-toolbar">
					<div class="btn-toolbar media-body">

						<div class="media-body">
							<time class="list-inline-item" align="right">${ Notice.NOTICE_DATE }</time>
						</div>
					</div>
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
	</c:forEach>
	<!--  본문 끝 -->


	<br>
	<br>

	<section class="responses comments" id="responses">
		<div class="container">

			<div class="comments" id="comments">
				<h3 class="section-title">댓글</h3>

				<form>
					<textarea class="form-control autosize" name="ReplyHelp" id="ReplyHelp"></textarea>
 

					<div class="clearfix">
						 
						<div class="float-right">
							<button class="btn btn-create-comment" onclick="return Reply()">댓글
								남기기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>


	<!--  모달 부분 -->
	<div id="modal" class="w3-modal"
		style="display: none; width: 300px; height: 300px">
		<div class="w3-modal-content">
			<div class="w3-container">

				<form id="FAQpwd">
					<h1>비밀글입니다.</h1>
					<p>아래 빈칸에 문의 글 등록 시 입력하신 비밀번호를 입력해 주세요.</p>
					<label>비밀번호</label> <input type="password" name="NOTICE_PWD"
						id="NOTICE_PWD">

					<div class="signup-form__catchphrase"></div>
			</div>
			<div>
				<a onclick="pwd()" class="signup-form__submit ga-event">확인</a>
				</form>
			</div>

		</div>
	</div>







	<!--  댓글 표시 영역 -->
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

	 function pwd(){
		 var modal = document.getElementById('modal');
		modal.style.display = "block";
		 var NOTICE_PWD=$("#NOTICE_PWD").val();
		
		 var NOTICE_NO= $("#boardArea").find("tr").children("td").eq(0).text();
		 
		 if(NOTICE_PWD==""){
			 alert("암호를 입력해주세요")
			 return false;
		 }
			
			$.ajax({
				url:"InquiryDetail.help",
				type:"post",
				dataType:"json",
				data:{"NOTICE_PWD": NOTICE_PWD,
					"NOTICE_NO":NOTICE_NO	},
				success:function(data){
				alert(data);
				}
				
				});
			return false;
		}
	 
	 </script> 
	 

	 <a href="InquiryList.help" id="retrunList">목록으로 돌아가기</a> </main>



	<jsp:include page="../common/footer.jsp" />

</body>
</html>