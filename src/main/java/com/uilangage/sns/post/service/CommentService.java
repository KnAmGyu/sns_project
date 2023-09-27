package com.uilangage.sns.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uilangage.sns.post.domain.Comment;
import com.uilangage.sns.post.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public Comment addComment( int postId, int userId, String content){
		
		Comment comment = Comment.builder()
							.postId(postId)
							.userId(userId)
							.content(content)
							.build();
		
		return commentRepository.save(comment);
	}
}
