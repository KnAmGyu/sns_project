package com.uilangage.sns.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uilangage.sns.like.domain.Like;
import com.uilangage.sns.like.repository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	
	public int deleteLike(int postId) {
		return likeRepository.deleteLike(postId);
	}
	
	public int countLike(int postId) {
		
		return likeRepository.selectCountLike(postId);
	}
	
	
	public int addLike(int postId, int userId) {
		
		
		
		return likeRepository.addLike(postId, userId);
	}
	
	
	public boolean isLike(int postId, int userId) {
		int count = likeRepository.selectCountLikeByUserId(postId, userId);
		
//		if(count == 0) {
//			return false;
//		}else {
//			return true;
//		}
		
		return count != 0;
		
	}
	
//	public void deleteLike(int id) {
//		
//		likeRepository.findById(id).ifPresent(like -> likeRepository.delete(like));
//		
//	}
	
	
	
}
