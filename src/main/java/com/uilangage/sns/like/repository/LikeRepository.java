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
	

	public int deleteLikeBypostId(@Param("postId") int postId);
	
	public int deleteLikeBypostIdAndUserId(
			@Param("postId") int postId
			, @Param("userId") int userId);
	
	
	
	
}
