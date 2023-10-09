package com.uilangage.sns.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uilangage.sns.comment.domain.Comment;
import com.uilangage.sns.comment.repository.CommentRepository;
import com.uilangage.sns.user.domain.User;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public Comment getpostIdByPost(int id) {
		Comment comment = commentRepository.findById(id).orElse(null);
		return comment;
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
