package com.uilangage.sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.uilangage.sns.post.domain.Post;

@Repository
public interface PostRepository {
	
	public int insertPost(
			@Param("userId") int userId
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public Post selectPost(@Param("postId") int postId);
	public List<Post> selectPostList();
	
	public int deletePost(@Param("postId") int postId);

}