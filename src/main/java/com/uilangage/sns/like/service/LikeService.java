package com.uilangage.sns.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uilangage.sns.like.domain.Like;
import com.uilangage.sns.like.repository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	
	public int countLike(int postId) {
		
		return likeRepository.selectCountLike(postId);
	}
	
	
	public int addLike(int postId, int userId) {
		
		
		
		return likeRepository.addLike(postId, userId);
	}
}
