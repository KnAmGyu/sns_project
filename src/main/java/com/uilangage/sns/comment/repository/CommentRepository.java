package com.uilangage.sns.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uilangage.sns.comment.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	public List<Comment> findAllByPostId(int postId);
	
	public List<Comment> findAll();

}
