package com.uilangage.sns.post.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uilangage.sns.comment.dto.CommentDetail;
import com.uilangage.sns.comment.service.CommentService;
import com.uilangage.sns.common.FileManager;
import com.uilangage.sns.like.service.LikeService;
import com.uilangage.sns.post.domain.Post;
import com.uilangage.sns.post.dto.PostDetail;
import com.uilangage.sns.post.repository.PostRepository;
import com.uilangage.sns.user.domain.User;
import com.uilangage.sns.user.service.UserService;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private LikeService likeService;
	
	public boolean deletePost(int postId, int userId) {
		
		commentService.deleteComment(postId);
		
		likeService.deleteLikeBypostId(postId);
		
		Post post = postRepository.findById(postId);
		
		FileManager.removeFile(post.getImagePath());
		
		postRepository.findById(postId).ifPresent(post -> postRepository.delete(post));
		
		if(post.getUserId() != 0 && post == null) {
			return true;
		}
		
		
	}
	
	public List<PostDetail> getPostList(int loginUserId){
		
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		List<PostDetail> postDetailList = new ArrayList<>(); 
		for(Post post:postList) {
			
			int userId = post.getUserId();
			User user = userService.getUserIdByPost(userId);
			// 좋아요 개수 조회
			int likeCount = likeService.countLike(post.getId());
			boolean islike = likeService.isLike(post.getId(), loginUserId);
			
			List<CommentDetail> commentList = commentService.getCommentDetailList(post.getId());
			
 		   	PostDetail postDetail = PostDetail.builder()
						   			.id(post.getId())
									.userId(userId)
									.content(post.getContent())
									.imagePath(post.getImagePath())
									.loginId(user.getLoginId())
									.likeCount(likeCount)
									.isLike(islike)
									.commentList(commentList)
									.build();
			
			postDetailList.add(postDetail);
		}
		 return postDetailList;
}
	
	
	public Post addPost(
			int userId
			, String content
			, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
						.userId(userId)
						.content(content)
						.imagePath(imagePath)
						.build();
		
		return postRepository.save(post);
		}
}
