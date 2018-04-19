<h1>팀 프로젝트명: FOREST(예술가 후원 웹사이트)</h1>

<h3>개발기간</h3>

4.9 ~ 5.9

<h3>개발 목표</h3>

스프링을 이용하여 독자가 신진 예술가를 후원하고, 예술가는 창작에 전념할 수 있는 웹사이트 개발

<h3>개발 환경</h3>

OS- Window8

Language- Java(Jdk 1.8), Javascript, Html5, css3

protocol : http

DataBase : Oracle 11g

web application server : tomcat 8.5

Library- Jquery 3.3.1, Jackson api 2.9.3 , disqus api, ckeditor api, aspectJ 1.8.13

Framework- Spring 5.04 

IDE- Eclipse

<hr>

<h3>DB설계</h3>

<img width="889" alt="default" src="https://user-images.githubusercontent.com/32535590/38807468-a604fbda-41b7-11e8-983e-dac236351259.png">

<hr>

<h3>구현기능</h3>

<h3>SHA512 암호화를 활용한 비밀번호 암호화</h3>

![1 sha512](https://user-images.githubusercontent.com/32535590/38807890-1c5783d8-41b9-11e8-9bf6-984500b22ff5.PNG)

![1 sha512](https://user-images.githubusercontent.com/32535590/38807884-179479fa-41b9-11e8-8972-2d9141b4944c.PNG)

빈칸에 비밀번호를 입력하고 확인을 누르면 비밀번호가 전송된다.

컨트롤러에서 encryptSHA512메소드를 호출해 암호화 한 후 데이터베이스로 전송.
 
비밀번호가 일치하면 암호 일치 완료라는 팝업창이 뜨고, 모달창을 닫을 수 있다 .

<hr>

<h3>명시적 트랜잭션 처리</h3>

![2](https://user-images.githubusercontent.com/32535590/38807895-1f15e218-41b9-11e8-939a-63a50cc985bd.PNG)

-TransactionDefinition 및 TransactionStatus를 이용해 트랜잭션의 시작과 커밋, 롤백을 명시적으로 처리. 

-Insert, delete, update 처리의 경우 트랜잭션 처리를 해서 성공 시 commit, 실패 시 rollback을 해줌

-propagation- 기본값인 Propagation.REQUIRED사용

-rollbackFor- Exception.class에서 지정한 예외가 발생하면 트랜잭션을 롤백시킨다.


<hr>

<h3>정규표현식과 유효성검사</h3>

![3](https://user-images.githubusercontent.com/32535590/38807898-21cf8e1e-41b9-11e8-9139-dcdba2930d2c.PNG)

![3](https://user-images.githubusercontent.com/32535590/38859732-a245073a-4269-11e8-8120-93c7b6df347b.PNG)


<유효성검사>

제목과 비밀번호 칸이 비어있으면,  경고창이 뜸.

<정규표현식 이용>

제목에 한글 이외에 다른 것을 입력하면 한글 제목만 가능합니다. 라는 경고창이 뜸

비밀번호에 숫자와 영문자 3-6개 조합으로 작성하지 않으면 [숫자와 영문자 조합으로 3-6자만 가능하다]는 경고창이 뜬다.

<hr>

<h3>AOP를 할용한 자동 예외처리 </h3>

Aspect = Advice +PointCut

![4 aop](https://user-images.githubusercontent.com/32535590/38807902-25ca8082-41b9-11e8-93f3-379cae1a071a.PNG)

스프링에서 기본 제공하는 aop 기능을 활용하면 타겟 객체의 메서드 호출 시에만 aop가 작동함

하지만 그 외의 경우에도 aop를 사용할 수 있으므로 메이븐에서 AspectJ 설치했음

AOP를 사용하면 비즈니스 로직(타겟) 외에 반복되는 구문을 편리하게 처리가능.

예외 처리의 경우 매번 예외 처리를 작성해야 하는데, AOP를 사용하면 특정 에러일 때 미리 입력한 값이 출력됨.

<hr>

<h3>disqus api를 활용한 계층형 댓글 </h3>

![5 disqus api](https://user-images.githubusercontent.com/32535590/38807908-2a50de44-41b9-11e8-86ea-9ff6d848591b.PNG)

![5](https://user-images.githubusercontent.com/32535590/38807910-2dab9188-41b9-11e8-9951-c026e256c252.PNG)

댓글 내용은 Disqus api 쪽 서버에 저장되며, 다른 페이지로 이동하였다가, 다시 화면으로 돌아와도 댓글이 남아있다.

<hr>

<h3>ckeditor api를 활용한 본문내용 편집</h3>

![6 ckeditor api](https://user-images.githubusercontent.com/32535590/38807916-31e938e0-41b9-11e8-86dd-ccac82a9905b.PNG)

ckeditor를 활용해 본문 작성 및 이미지 첨부가 가능하고, 변경 된 내용을 디비에 저장 가능하다

<hr>

<h3>사용자 편의를 위한 마이바티스 동적 SQL및 페이징 처리</h3>

![7](https://user-images.githubusercontent.com/32535590/38807920-34fb133c-41b9-11e8-9fbf-2211841feea2.PNG)

![7](https://user-images.githubusercontent.com/32535590/38808567-a8ecc522-41bb-11e8-8776-4b9e50fe6e52.PNG)

1 listCount로 조건에 맞는 글 개수를 데이터베이스에서 가져옴

2 SearchCondtion 객체와 PageInfo 객체를 searchHelpResultList 메소드에 인자로보내

결과값을 전송받은 후 list 객체에 저장.

<hr>

<h3>개선사항</h3>

1 코드 리팩토링

2 테스트 코드 추가 (현재는 aop 처리 이용해 어떤 리턴값을 받아오는지 체크하고 있음)

3 CSRF 방지 기능 추가

4 스프링 캐시를 활용해, 자주 사용하는 기능 빠르게 로딩하기

5 리눅스 서버에 배포(현재 톰캣 , 오라클, JDK 등은 설치했음)

6 AWS EC2에 배포 및 서비스 호스팅(프리티어로)

7 관리자 기능 추가 구현(현재 다른 팀원이 하고 있으나, 프로젝트 이후 개별적으로 구현)
