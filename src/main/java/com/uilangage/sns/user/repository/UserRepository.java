package com.uilangage.sns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uilangage.sns.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
