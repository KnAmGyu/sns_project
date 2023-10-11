package com.uilangage.sns.post.dto;



import java.util.List;

import com.uilangage.sns.comment.dto.CommentDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDetail {
	
	private int id;
	private int userId;
	private String loginId;
	private String content;
	private String comment;
	private String imagePath;
	private int likeCount;
	private boolean isLike;
	private List<CommentDetail> commentList;
	
	
}
