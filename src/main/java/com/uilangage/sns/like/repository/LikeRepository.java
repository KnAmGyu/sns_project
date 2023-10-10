package com.uilangage.sns.like.repository;

import org.apache.ibatis.annotations.Param;

import com.uilangage.sns.like.domain.Like;

public interface LikeRepository {

	
	public int addLike(
			@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectCountLike(@Param("postId") int postId);
	
}
