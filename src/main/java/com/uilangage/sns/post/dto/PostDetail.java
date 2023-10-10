package com.uilangage.sns.post.dto;



import java.util.List;

import com.uilangage.sns.comment.domain.Comment;

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
	private List<Comment> commentList;
	//private List<CommentDetail> commentList;
	
	
}
