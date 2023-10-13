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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="contents d-flex">
			<div class="post-layout">
				<div class="input-layout border rounded mt-5">
					<textarea class="contents-input form-control mt-3 border-0" row="10" id="contentInput""></textarea>
					<div class="d-flex justify-content-between my-3">
						<input type="file" class="ml-2" id="fileInput">
						<a type="button" class="img-btn mr-3" id="saveBtn"><img src="/static/images/ic032-save.png"></a>
					</div>
				</div>
			<c:forEach var="post" items="${postList }" >
				<div class="card timeline-content mt-3">
					<div class="d-flex justify-content-between p-2">
						<div class="userId-area">${post.loginId }</div>
						
						<%-- 로그인 한 사용자의 게시글에만 more-btn 노출 --%>
						<c:if test="${post.userId eq userId }">
						<i class="bi bi-three-dots-vertical more-btn" data-post-id="${post.id }"></i>
						</c:if>
						<!--  data-toggle="modal" data-target="#moreMadal" -->
					</div>
					
					<div class="image-area"><img width="100%" src="${post.imagePath }"></div>
					<div class="content-area p-2"><b>${post.loginId }</b> ${post.content }</div>
					<div class="like-area p-2">
						<c:choose> 
							<c:when test="${post.like }">
								<i class="bi bi-heart-fill liked-icon text-danger" data-post-id="${post.id }"></i>
							</c:when>
							<c:otherwise>
								<i class="bi bi-heart like-icon" data-post-id="${post.id }"></i>	
							</c:otherwise>
						</c:choose>
						
						좋아요 ${post.likeCount }개
					</div>
					<div class="coment-area">
						<div class="p-2 coment-tit">댓글</div>
						<div class="px-2 small my-2">
							<c:forEach var="comment" items="${post.commentList }">
								<div>
									<b>${comment.loginId }</b> ${comment.content }
								</div>
							</c:forEach>
						</div>
						
						<div class="d-flex">
							<input type="text" class="form-control comment-input" id="commentInput${post.id }" >
							<button type="button" class="btn btn-secondary comment-btn" data-post-id="${post.id }">게시</button>
						</div>
					</div>
				</div>
			</c:forEach>	
				
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		
		
		
		<!-- Modal -->
		<div class="modal fade" id="moreMadal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-body text-center">
		        <a href="#" id="deleteBtn" data-post-id="">삭제하기</a>
		      </div>
		      
		    </div>
		  </div>
		</div>
		
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function(){
		$(".liked-icon").on("click", function(){
			let postId = $(this).data("post-id");
			
			$.ajax({
				type:"delete"
				, url:"/post/unlike"
				, data:{"postId":postId}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else{
						alert("취소 실패");
					}
				}
				, error:function(){
					alert("취소 에러");
				}
			});
			
		});
		
		
		$(".more-btn").on("click",function(){
			//모달에 있는 삭제하기 링크 태그에 postId를 data 속성에 추가한다.
			// data-post-id
			let postId = $(this).data("post-id");
			
			$("#deleteBtn").data("post-id", postId)
			
		});
		
		
		$("#deleteBtn").on("click",function(){
			let postId = $(this).data("post-id"); 
			
			$.ajax({
				type:"delete"
				, url:"/post/delete"
				, data:{"postId":postId}
				, success:function(data){
				if(data.result == "success"){
					location.reload();
				}else{
					alert("삭제 실패");
				}
			}
			, error:function(){
				alert("삭제 에러");
			}
			});
			
			
			
		});
		
		$(".comment-btn").on("click", function(){
			
			let postId = $(this).data("post-id");
			let comment = $("#commentInput" + postId).val();
			
			if(comment == ""){
				alert("댓글 내용을 입력하세요.");
				return ;
			}
			
			$.ajax({
				type:"post"
				, url:"/post/comment/create"
				, data:{"postId":postId,"content":comment}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else{
						alert("댓글작성 실패");
					}
				}
				, error:function(){
					alert("댓글작성 에러");
				}
			});
			
		});
		
		$(".like-icon").on("click",function(){
			
			let postId = $(this).data("post-id");
			
			$.ajax({
				type:"post"
				, url:"/post/like"
				, data:{"postId":postId}
				, success:function(data){
					
					if(data.result == "success"){
						location.reload();
					}else{
						alert("좋아요 실패");
					}
					
					
				}
				, error:function(){
					alert("좋아요 에러");
				}
			});
			
		});
		
		
		$("#saveBtn").on("click",function(){
			
			let content = $("#contentInput").val();
			
			let file = $("#fileInput")[0];
			
			if(content == ""){
				alert("내용을 입력하세요");
				return ;
			}
			// 파일에 대한 유효성 검사
			if(file.files.length == 0){
				alert("파일을 선택해주세요")
				return ;
			}
			
			//{"content":content}
			let formData = new FormData();
				formData.append("content", content);
				formData.append("imageFile", file.files[0]);
			
			$.ajax({
				type:"post"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"  // 파일 업로드 필수 옵션
				, processData:false  // 파일 업로드 필수 옵션
				, contentType:false   // 파일 업로드 필수 옵션
				, success:function(data){
					if(data.result == "success"){
						location.reload();
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