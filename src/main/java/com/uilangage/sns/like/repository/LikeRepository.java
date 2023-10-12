package com.uilangage.sns.like.repository;

import org.apache.ibatis.annotations.Param;

import com.uilangage.sns.like.domain.Like;

public interface LikeRepository {

	
	public int addLike(
			@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectCountLike(@Param("postId") int postId);
	
	public int selectCountLikeByUserId(
			@Param("postId") int postId
			, @Param("userId") int userId);

	public int findByPostId(@Param("postId")int postId);
	
	public int deleteById();

	public int deleteLike(@Param("postId") int postId);
	
//	public int findById(@Param("id")int id);
}
