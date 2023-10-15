package com.uilangage.sns.like.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository {
	
	public int insertLike(
			@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectCountLike(
			@Param("postId") int postId);
	
	public int selectCountLikeByUserId(
			@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int deleteLikeByPostId(@Param("postId") int postId);
	
	public int deleteLikeByPostIdAndUserId(
			@Param("postId") int postId
			, @Param("userId") int userId);

}