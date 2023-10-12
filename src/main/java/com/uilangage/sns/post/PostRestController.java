package com.uilangage.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uilangage.sns.post.domain.Post;
import com.uilangage.sns.post.service.PostService;
@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostService postService;
	
	public Map<String, String> deletePost(@RequestParam("id")int id){
		
		 postService.deletePostList(id);
		
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
