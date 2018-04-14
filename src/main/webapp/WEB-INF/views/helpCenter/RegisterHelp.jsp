<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html dir="ltr" lang="ko">
<head>
 <style>
 div{
 padding:7px;
 }
 </style>
 
</head>
<body class="">

<jsp:include page="../common/header.jsp"/>

 
  <div class="form">
   <h1>
    문의 등록
  </h1>
    <form id="new_request" data-form="" class="request-form" action="PersonalInquiryList.help" accept-charset="UTF-8" method="post" encType="multipart/form-data">
    
  <div class="form-field string required request_anonymous_requester_email">
 
</div>
 
    <div class="form-field string  required  request_custom_fields_114103854754" >
      
    </div>
     
 
    <div class="form-field string  required  request_subject" >
      <label for="request_subject">제목</label>
      <input type="text" name="NOTICE_TITLE" id="request_subject" maxlength="150" size="150" aria-required="true" aria-describedby="request_subject_hint" />
      
      <p id="request_subject_hint">보내실 문의의 제목을 입력하세요.</p>
    </div>

    
      <div class="suggestion-list" data-hc-class="searchbox" data-hc-suggestion-list="true"></div>
    
  
    <div class="form-field text  required  request_description" >
      <label for="request_description">문의 내용</label>
      <textarea name="NOTICE_CONTENT" id="request_description" aria-required="true" aria-describedby="request_description_hint">
</textarea>
      
      <p id="request_description_hint">요청에 관한 세부 정보를 입력하세요. 내용을 자세히 적어 주시면 지원팀이 빠르게 도움을 드리는 데 효과적입니다.</p>
    </div>

  <div class="form-field string  required  request_subject" >
      <label for="request_password">비밀번호</label>
      <input type="password" name="NOTICE_PWD" id="request_subject" maxlength="150" size="150" aria-required="true" aria-describedby="request_subject_hint" />
      
      <p id="request_subject_hint">문의글을 확인할 비밀번호를 입력하세요</p>
    </div>
    
  

  <div class="form-field">
  <label>첨부 파일</label>
  <div id="upload-dropzone" class="upload-dropzone">
  <input type="file" name="photo"/>
 
</div>
 
</div>

 
  <footer><input type="submit" name="commit" value="제출" /></footer>

</form>
 

  </div>

  
</div>

  </main>

<!-- footer -->
 
  <jsp:include page="../common/footer.jsp"/>

</body>
</html>