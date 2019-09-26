<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>가입이 완료되었습니다.</h1><br>
	<h3>아이디: ${userVO.uid}</h3><br>
	<h3>이름: ${userVO.uname}</h3><br>
	<a href="..">홈 화면으로</a>
</body>
</html>