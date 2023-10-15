package com.uilangage.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uilangage.sns.comment.domain.Comment;
import com.uilangage.sns.comment.service.CommentService;

@RestController
public class CommentRestController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/comment/create")
	public Map<String, String> createComment(
			@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		int count = commentService.addComment(userId, postId, content);
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	

}