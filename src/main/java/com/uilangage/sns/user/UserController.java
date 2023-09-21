package com.uilangage.sns.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/join-view")
	public String joinInput() {
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String loginInput(){
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/user/login-view";
		
	}
	
	
}
