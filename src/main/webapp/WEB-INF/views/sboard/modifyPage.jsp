<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
</head>
<body>

<%@ include file="../include/header.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<!--  left column -->
			<div class="col-md-12">
				<!-- general form elements -->
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">게시글 수정</h3>
					</div>

					<form role="form" action="modifyPage" method="post">
					
						<input type="hidden" name="page" value="${cri.page}">
						<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
						<input type="hidden" name="searchType" value="${cri.searchType}">
						<input type="hidden" name="keyword" value="${cri.keyword}">
						 
						<div class="box-body">
							<div class="form-group">
								<label for="exampleInputEmail1">게시물 번호</label> <input type="text"
									name='bno' class="form-control" value="${boardVO.bno}"
									readonly="readonly">
							</div>
							<div class="form-group">
								<label for="exampleInputEamil1">작성자</label> <input
									type="text" name='writer' class="form-control"
									value="${boardVO.writer}"
									readonly="readonly">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">제목</label> <input type="text"
									name='title' class="form-control" value="${boardVO.title}">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">내용</label>
								<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
							</div>
						</div>
						<!-- /.box-body -->
					</form>

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">저장</button>
					<button type="submit" class="btn btn-warning">취소</button>
				</div>
			</div>
			<!-- /.col(left) -->	
		</div>
		<!-- /.row -->		
	</section>		
	<!-- /.content -->
	<%@include file="../include/footer.jsp" %>
	
<script type="text/javascript">

	$(document).ready(function(){
		
		let formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click",function(){
			self.location = "/sboard/list?page=${cri.page}&perPageNum=${cri.perPageNum}"
				+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
		});
		
		$(".btn-primary").on("click",function(){
			//위에 html form태그를 보면 method가 post로 전송되어 controller에 modifyPOST메서드와 매핑이 된다.
			formObj.submit();
		});
		
	});

</script>
</body>
</html>