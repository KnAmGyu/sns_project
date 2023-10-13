package com.uilangage.sns.post.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="post")
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	private String content;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@UpdateTimestamp
	@Column(name="createdAt", updatable=false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Date updatedAt;

		
	}

