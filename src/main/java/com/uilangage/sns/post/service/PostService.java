package com.uilangage.sns.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uilangage.sns.common.FileManager;
import com.uilangage.sns.post.domain.Post;
import com.uilangage.sns.post.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> getPostList(int userId){
		
	
		return postRepository.findByUserId(userId);
		
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
