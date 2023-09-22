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
			<div class="w-500">
				<img src="https://scsgozneamae10236445.cdn.ntruss.com/data2/content/image/2019/01/29/.cache/512/201901290839179.jpg">
			</div>
			<div class="input-box-wrap">
				<h2 class="text-center my-5">LangstarGram</h2>
				<div class="input-box">
					<form id="loginForm">
						<input type="text" placeholder="아이디" class="form-control mt-4" id="loginIdInput">
						<input type="password" placeholder="비밀번호" class="form-control mt-4" id="passwordInput">
					
						<button type="submit" class="btn btn-secondary btn-block mt-4" id="loginBtn">로그인</button>
					</form>
					<div class="d-block mt-2 text-center">
						<a href="/user/join-view">회원가입</a>
					</div>
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
			// $("#loginBtn").on("click", function(){
			$("#loginForm").on("submit", function(e){	
				
				// form 태그가 가진 페이지 이동 기능을 막자
				e.preventDefault();
				
				let loginId = $("#loginIdInput").val();
 				let password = $("#passwordInput").val();
 				
 				if(loginId == ""){
 					alert("아이디를 입력하세요");
 					return ;
 				}
 				if(password == ""){
 					alert("비밀번호를 입력하세요");
 					return ;
 				}
 				
 				$.ajax({
 					type:"post"
 					, url:"/user/login"
 					, data:{"loginId":loginId,"password":password}
 					, success:function(data){
 						if(data.result == "success"){
 							location.href = "/post/timeline-view"
 						}else{
 							alert("아이디 비밀번호를 확인해 주세요");
 						}
 					}
 					, error:function(){
 						alert("로그인 과정 에러");
 					}
 				});
				
			});
		});
	
	</script>
</body>
</html>