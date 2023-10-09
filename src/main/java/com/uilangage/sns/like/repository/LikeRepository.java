package com.uilangage.sns.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uilangage.sns.like.domain.Like;

public interface LikeRepository  extends JpaRepository<Like, Integer> {

}
