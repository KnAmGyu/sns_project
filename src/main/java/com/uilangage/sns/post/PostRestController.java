package com.uilangage.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uilangage.sns.post.domain.Post;
import com.uilangage.sns.post.service.PostService;

@RestController
public class PostRestController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam("content") String content
//			, @RequestParam("imagePath") String imagePath
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		
		Post post = postService.addPost(userId, content);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(post != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	
}
