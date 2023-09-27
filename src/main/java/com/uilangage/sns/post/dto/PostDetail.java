package com.uilangage.sns.post.dto;



import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDetail {
	
	private int id;
	private int userId;
	private String loginId;
	private String content;
	private String imagePath;
	
	
}
