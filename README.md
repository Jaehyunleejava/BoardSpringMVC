# BoardSpringMVC

### Spring을 이용한 게시판 만들기
- 개발환경
1. DB: mysql
2. Server: Tomcat 8.5
3. IDE: Eclipse
4. Framework: Spring, MyBatis
5. 사용언어 및 라이브러리: Java JSP Javascript JSTL jQuery

#### 메인화면
![Main](./ReadMeImage/메인화면.png)

#### 게시글목록
![list](./ReadMeImage/게시글목록.png)

#### 게시글검색기능
![search](./ReadMeImage/게시글상세보기.png)

#### 게시글상세보기
![detail](./ReadMeImage/게시글상세보기.png)

## 주요기능
- 댓글처리 -> RESTful 방식으로 Ajax 댓글 처리
- 로그인기능 ->
Spring Intercepter를 이용해서 구현
- 검색 처리 -> MyBatis 동적 SQL을 이용
- 댓글 조회 수 및 댓글 수 트랜잭션처리
- 파일 업로드

> 참고: 코드로 배우는 스프링 웹 프로젝트
