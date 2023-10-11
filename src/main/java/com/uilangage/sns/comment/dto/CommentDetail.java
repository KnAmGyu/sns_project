package com.uilangage.sns.comment.dto;



import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDetail {
	
	private int id;
	private int userId;
	private String loginId;
	private String content;
	
}
