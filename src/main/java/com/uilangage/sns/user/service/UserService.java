package com.uilangage.sns.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uilangage.sns.common.EncryptUtils;
import com.uilangage.sns.user.domain.User;
import com.uilangage.sns.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User getUserIdByPost(int id) {
		User user = userRepository.findById(id);
		return user;
	}
	
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.countByLoginId(loginId);
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	public User getUser(String loginId, String password){
		
			String encryptPassword = EncryptUtils.md5(password);
		
			User user = userRepository.findByLoginIdAndPassword(loginId, encryptPassword).orElse(null);
			
			return user;
	}
	
	
	public User addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		User user = User.builder()
						.loginId(loginId)
						.password(encryptPassword)
						.name(name)
						.email(email)
						.build();
		
		return userRepository.save(user);
	}
	
	

}
