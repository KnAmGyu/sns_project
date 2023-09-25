<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>         
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LangstarGram</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="contents d-flex">
			<div class="post-layout">
				<div class="input-layout">
					<textarea class="contents-input form-control mt-3" row="10" id="contentInput""></textarea>
					<div class="d-flex justify-content-between my-3">
						<input type="file" class="ml-2">
						<a type="button" class="img-btn mr-3" id="saveBtn"><img src="https://cdns.iconmonstr.com/wp-content/releases/preview/2013/240/iconmonstr-save-6.png"></a>
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
		$("#saveBtn").on("click",function(){
			
			let content = $("#contentInput").val();
			
			if(content == ""){
				alert("내용을 입력하세요");
				return ;
			}
			
			$.ajax({
				type:"post"
				, url:"/post/create"
				, data:{"content":content}
				, success:function(data){
					if(data.result == "success"){
						location.href = "/post/timeline-view";
					}else{
						alert("메모작성 실패");
					}
				}
				, error:function(){
					alert("메모작성 에러");
				}
			});
			
		});
		
	});
	</script>
</body>
</html>