# BoardSpringMVC

### Spring을 이용한 게시판 만들기
- 개발환경
1. DB: mysql
2. Server: Tomcat 8.5
3. IDE: Eclipse
4. Framework: Spring, MyBatis
5. 사용언어 및 라이브러리: Java JSP Javascript JSTL jQuery

#### 메인화면
![메인화면](https://user-images.githubusercontent.com/45728768/65704206-0e048d00-e0c1-11e9-84fd-982576e8802b.PNG)


#### 게시글목록
![image](https://user-images.githubusercontent.com/45728768/65703922-8f0f5480-e0c0-11e9-8ae2-c7ca71bf4a12.PNG)


#### 게시글검색기능
![image](https://user-images.githubusercontent.com/45728768/65703948-9c2c4380-e0c0-11e9-8c73-d18fed181de1.PNG)


#### 게시글상세보기
![image](https://user-images.githubusercontent.com/45728768/65703981-ac442300-e0c0-11e9-9411-f159d605d2fd.PNG)

#### 게시글댓글기능
![image](https://user-images.githubusercontent.com/45728768/65704118-e9101a00-e0c0-11e9-9807-acd3fa8db928.png)

## 주요기능
- 댓글처리 -> RESTful 방식으로 Ajax 댓글 처리
- 로그인기능 ->
Spring Intercepter를 이용해서 구현
- 검색 처리 -> MyBatis 동적 SQL을 이용
- 댓글 조회 수 및 댓글 수 트랜잭션처리
- 파일 업로드

> 참고: 코드로 배우는 스프링 웹 프로젝트
