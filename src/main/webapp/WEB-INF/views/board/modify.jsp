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
						<div class="box-body">
							<div class="form-group">
								<label for="exampleInputEmail1">BNO</label> <input type="text"
									name='bno' class="form-control" value="${boardVO.bno}"
									readonly="readonly">
							</div>
							<div class="form-group">
								<label for="exampleInputEamil1">Writer</label> <input
									type="text" name='writer' class="form-control"
									value="${boardVO.writer}"
									readonly="readonly">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">TITLE</label> <input type="text"
									name='title' class="form-control" value="${boardVO.title}">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Content</label>
								<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
							</div>
						</div>
						<!-- /.box-body -->
					</form>

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">Save</button>
					<button type="submit" class="btn btn-warning">Cancel</button>
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
			self.location = "/board/listAll";
		});
		
		$(".btn-primary").on("click",function(){
			//위에 html form태그를 보면 method가 post로 전송되어 controller에 modifyPOST메서드와 매핑이 된다.
			formObj.submit();
		});
		
	});

</script>
</body>
</html>