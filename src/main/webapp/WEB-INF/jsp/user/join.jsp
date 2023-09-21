<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Langstargram</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="contents d-flex">
			<div class="input-box">
				<h2 class="my-4 text-center">회원 가입</h2>
				<div class="d-flex">
					<input type="text" placeholder="아이디" class="form-control mt-4" id="loginIdInput"><button type="button" class="btn btn-info btn-sm ml-2 mt-4" id="isDuplicateBtn">중복확인</button>
				</div>
				<div class="small text-success d-none" id="availableText">사용이 가능한 아이디 입니다.</div>
				<div class="small text-danger d-none" id="duplicateText">중복된 아이디 입니다.</div>
				<input type="password" placeholder="비밀번호" class="form-control mt-4" id="passwordInput">
				<input type="password" placeholder="비밀번호확인" class="form-control mt-4" id="passwordConfirmInput">
				<input type="text" placeholder="이름" class="form-control mt-4" id="nameInput">
				<input type="text" placeholder="이메일" class="form-control mt-4" id="emailInput">
			
				<button type="button" class="btn btn-secondary btn-block mt-4" id="joinBtn">회원가입</button>
				<div class="d-block my-4 text-center">
					<span>아직 계정이 없으신가요?</span> <a href="/user/login-view">로그인하러가기</a>
				</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<script>
		$(document).ready(function(){
			// 중복확인 체크 여부 
			var isCheckDuplicate = false;
			var isDuplicate = true;
			
			$("#isDuplicateBtn").on("click", function(){
				let id = $("#loginIdInput").val();
				
				if(id == ""){
					alert("아이디를 입력하세요");
					return ;
				}
				
				$.ajax({
					type:"get"
					, url:"/user/duplicate-id"
					, data:{"loginId":id}
					, success:function(data){
						
						isCheckDuplicate = true;
						
						if(data.isDuplicate){
							// 중복되었다.
							$("#duplicateText").removeClass("d-none");
							$("#availableText").addClass("d-none");
							
							isDuplicate = true;
						}else{
							// 중복되지 않았다.
							$("#duplicateText").addClass("d-none");
							$("#availableText").removeClass("d-none");
							
							isDuplicate = false;
						}
					}
					, error:function(){
						alert("중복확인에러");
					}
				});
				
				
				
			});
			
			$("#joinBtn").on("click", function(){
				
				let loginId = $("#loginIdInput").val();
				let password = $("#passwordInput").val();
				let passwordConfirm = $("#passwordConfirmInput").val();
				let name = $("#nameInput").val();
				let email = $("#emailInput").val();
				
				if(loginId == ""){
					alert("아이디를 입력하세요");
					return ;
				}
				
				// 중복체크가 안된 경우
				if(!isCheckDuplicate){
					alert("아이디 중복확인을 해주세요");
					return ;
				}
				
				// 중복된 id인 경우 
				if(isDuplicate){
					alert("중복된 아이디 입니다.");
					return ;
				}
				
				if(password == ""){
					alert("비밀번호를 입력하세요");
					return ;
				}

				if(password != passwordConfirm){
					alert("비밀번호가 일치하지 않습니다");
					return;
				}
				
				if(name == ""){
					alert("이름을 입력하세요");
					return ;
				}
				if(email == ""){
					alert("이메일을 입력하세요");
					return ;
				}
				$.ajax({
					type:"post"
					, url:"/user/join"
					, data:{"loginId":loginId, "password":password, "name":name, "email":email}
					, success:function(data){
						if(data.result == "success"){
							location.href = "/user/login-view";
						}else{
							alert("회원가입 실패!!")
						}
					}
					, error:function(){
						alert("회원가입 처리 에러");
					}
				});
				
			});
		});
	</script>
	
	
</body>
</html>