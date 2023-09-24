package com.uilangage.sns.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uilangage.sns.post.domain.Post;
import com.uilangage.sns.post.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	
	public Post addPost(
			int userId
			, String content) {
		
		
		Post post = Post.builder()
						.userId(userId)
						.content(content)
						.build();
		
		return postRepository.save(post);
		}
}
