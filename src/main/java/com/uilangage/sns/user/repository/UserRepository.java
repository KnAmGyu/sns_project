package com.uilangage.sns.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uilangage.sns.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	public int countByLoginId(String loginId);
	
	public Optional<User> findByLoginIdAndPassword(String loginId, String password);
	
	
	
}
