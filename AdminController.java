package com.claims.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claims.models.Admin;
import com.claims.services.AdminUserService;
import com.claims.services.ClaimsService;
import com.claims.services.MemberService;

@Controller
public class AdminController {
	
	@Autowired AdminUserService userService;
	@Autowired HttpSession session;
	@Autowired MemberService memberService;
	@Autowired ClaimsService claimsService;

	@GetMapping("/")
	private String home() {
		return "home";
	}
	@GetMapping("/index")
	private String index() {
		return "index";
	}

	
	@PostMapping("/index")
	public String Validate(String userid,String pwd,RedirectAttributes redirAttrs) {
		System.out.println(userid);
		Admin user=userService.validate(userid, pwd);
		System.out.println(user);
		if(user==null) {
			redirAttrs.addFlashAttribute("error", "Invalid username or password..!!");
			return "redirect:/";
		}else {
			session.setAttribute("userid", user.getUserid());
			session.setAttribute("uname", user.getUname());
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("members", memberService.totalMembers());
		model.addAttribute("claims", claimsService.totalClaims());
		model.addAttribute("pendings", claimsService.totalPending());
		model.addAttribute("approved", claimsService.totalApproved());
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
