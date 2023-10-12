package com.uilangage.sns.comment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uilangage.sns.comment.domain.Comment;
import com.uilangage.sns.comment.dto.CommentDetail;
import com.uilangage.sns.comment.repository.CommentRepository;
import com.uilangage.sns.post.domain.Post;
import com.uilangage.sns.user.domain.User;
import com.uilangage.sns.user.service.UserService;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	
	
	public void deleteComment(int id) {

		commentRepository.findById(id).ifPresent(comment -> commentRepository.delete(comment));
	}
	
	
	public List<CommentDetail> getCommentDetailList(int postId){
		
		List<Comment> commentList = commentRepository.findAllByPostId(postId);
		
		List<CommentDetail> commentDetailList = new ArrayList<>();
		for(Comment comment:commentList) {
			int userId = comment.getUserId();
			User user = userService.getUserIdByPost(userId);
			
			CommentDetail commentDetail = CommentDetail.builder()
											.id(comment.getId())
											.userId(comment.getUserId())
											.loginId(user.getLoginId())
											.content(comment.getContent())
											.build();
			commentDetailList.add(commentDetail);
		}
		return commentDetailList;
		
	}
	
	
	public List<Comment> getCommentList(int postId) {
		
		return  commentRepository.findAllByPostId(postId);
		
	}
	
	public Comment addComment(
			int postId
			, int userId
			, String content) {
		
		
		
		Comment comment = Comment.builder()
						.postId(postId)
						.userId(userId)
						.content(content)
						.build();
		
		return commentRepository.save(comment);
		}
}
