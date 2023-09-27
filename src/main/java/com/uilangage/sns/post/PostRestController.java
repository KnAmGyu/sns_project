package com.uilangage.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uilangage.sns.post.domain.Comment;
import com.uilangage.sns.post.domain.Post;
import com.uilangage.sns.post.service.CommentService;
import com.uilangage.sns.post.service.PostService;
@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	
	@GetMapping("/comment/create")
	public Map<String, String> createComment(
			@RequestParam ("postId") int postId
			, @RequestParam ("content") String content
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Comment comment = commentService.addComment(postId, userId, content); 
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(comment != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	
	
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam("content") String content
			, @RequestParam(value="imageFile", required=false) MultipartFile file
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		
		Post post = postService.addPost(userId, content, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(post != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	
}
