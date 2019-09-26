<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">

	let idck = 0; //중복 체크 했는지 확인하는 변수, 확인하고 이상 없으면 1
	
	function click_join(){
		let id = $("#uid").val();
		
		if(idck ==0){
			alert('아이디 중복 확인을 해주세요.');
		} else{
			document.reg.action="joinUs";
			document.reg.method="post"; 
			document.reg.submit();

		}
		
	}
	
	function fn_process() {
		let id = $("#uid").val();
		
		if(id == '') {
			alert("ID를 입력하세요");
			return;
		}
		
		$.ajax({
			
			type: "post",
			async: true,
			url: "/user/idCheck",
			data:{uid : id},
			success: function (data) {
				if(data==1){
					$('#message').text("사용할 수 있는 ID입니다.");
					$('#btn_duplicate').prop("disabled",true);
					idck =1;
				} else {
					$('#message').text("사용할 수 없는 ID입니다.");
				}
			}
			
		});
	}
</script>
</head>
<body>
<h1>회원가입창</h1>
	<form>
		아이디: <input type="text" id="uid" name="uid" placeholder="아이디를 입력하세요">
			<input type="button" id="btn_duplicate" value="중복확인" onclick="fn_process();"><br>
		비밀번호: <input type="password" id="upw" name="upw" placeholder="비밀번호를 입력하세요"><br>
		이름: <input type="text" id="uname" name="uname" placeholder="이름을 입력하세요"><br>
		<button type="submit" onclick="click_join();">등록</button>
	</form>

	
</body>
</html>