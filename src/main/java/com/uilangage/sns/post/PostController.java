package com.uilangage.sns.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uilangage.sns.post.dto.PostDetail;
import com.uilangage.sns.post.service.PostService;
@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	
	
	
	@GetMapping("/create-view")
	public String postInput() {
		return "post/input";
	}
	
	
	@GetMapping("/timeline-view")
	public String timeline(Model model
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<PostDetail> postList = postService.getPostList(userId);
		model.addAttribute("postList", postList);
		
		return "/post/timeline";
	}
	
}
