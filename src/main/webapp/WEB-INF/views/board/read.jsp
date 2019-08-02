<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
						<h3 class="box-title">READ BOARD</h3>
					</div>
					
					<form role="form" method="post">
						<input type="hidden" name='bno' value="${boardVO.bno}">
					</form>
					
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label>
							<input type="text" name='title' class="form-control" value="${boardVO.title}" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEamil1">Writer</label>
							<input type="text" name='writer' class="form-control" value="${boardVO.writer}" readonly="readonly">
						</div>
					</div>
				</div>
				<!-- /.box-body -->
				
				<div class="box-footer">
					<button type="submit" class="btn btn-warning">Modify</button>
					<button type="submit" class="btn btn-danger">REMOVE</button>
					<button type="submit" class="btn btn-primary">LIST ALL</button>
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
			formObj.attr("action","/board/modify");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		$(".btn-danger").on("click",function(){
			formObj.attr("action","/board/remove");
			formObj.submit();
		});
		
		$(".btn-primary").on("click",function(){
			self.location = "/board/listPage";
		});
		
	});
	
</script>
</body>
</html>