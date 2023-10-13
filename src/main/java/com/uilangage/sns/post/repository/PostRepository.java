package com.uilangage.sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uilangage.sns.post.domain.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post, Integer>{

	public List<Post> findAllByOrderByIdDesc();
	
	public Post findById(int id);
	
//	public Post findById(int postId);
	
	public int deleteByPostId(int postId);
	
}
