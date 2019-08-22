<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Home</title>
</head>
<body>

	<%@ include file="include/header.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<!--  left column -->
			<div class="col-md-12">
				<!-- general form elements -->
				
				<div class="row">
				  <div class="col-sm-9">
				    <div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">main</h3>
						</div>
					    <div class="row">
					    <div class="col-xs-8 col-sm-6">
					      Level 2: .col-xs-8 .col-sm-6
					    </div>
					    
					    <div class="col-xs-4 col-sm-6">
							<div class="box-header with-border">
							<c:if test="${empty login}">
								<h2 class='box-title'>로그인 창</h2>
									<form action="/user/loginPost" method="post">
										<div class="form-gorup has-feedback">
											<input type="text" name="uid" class="form-control" placeholder="아이디"
											 style="width:420px; height:40px;">
											<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
										</div>
										<div class="form-group has-feedback">
											<input type="password" name="upw" class="form-control" placeholder="비밀번호"
											 style="width:420px; height:40px;">
											<span class="glyphicon glyphicon-lock form-control-feedback"></span>
										</div>
										<div class="row">
											<div class="col-xs-8">
												<div class="checkbox icheck">
													<label>
														<input type="checkbox" name="useCookie"> 자동 로그인
													</label>
												</div>
											</div><!-- /.col -->
											<div class="col-xs-4">
												<button type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
											</div><!-- /.col -->
										</div>
									</form>
								<button type="submit" id="signUpBtn"class="btn btn-primary btn-block btn-flat">회원가입</button>
							</c:if>
							
							<c:if test="${not empty login}">
								<form action="/user/logout" method="post">
									<button type="submit" class="btn btn-primary btn-block btn-flat">로그아웃</button>
								</form>
							</c:if>
								
							</div>
					    </div>
					    
					</div>
				  </div>
				</div>
				
				

					

				
				
			</div><!-- /.col(left) -->	
		</div>		<!-- /.row -->
	</section>		<!-- /.content -->
	
	<%@include file="include/footer.jsp" %>
	
	<script>
		$('#signUpBtn').click(function(){
			location.href="/user/joinPage";
		});
	</script>
</body>
</html>
