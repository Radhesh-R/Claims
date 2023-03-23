package com.claims.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claims.models.Insurances;
import com.claims.models.Member;
import com.claims.services.MemberService;

@Controller
public class MemberController {

	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	
	@GetMapping("/memberlogin")
	private String memberlogin() {
		return "memberlogin";
	}
	@PostMapping("/memberlogin")
	private String membervalidate(String userid,String pwd,RedirectAttributes redirAttrs) {
		System.out.println(userid);
		if(userid.equals("member") && pwd.equals("member")) {
			return "header1";
		}else
		return "redirect:/memberlogin";
	}
	@GetMapping("/newmember1")
	public String addMember1(Model model) {
		model.addAttribute("memberid", memberService.generateMemberId());
		model.addAttribute("insurances", memberService.getInsurances());
		return "newmember1";
	}
	@PostMapping("/newmember1")
	public String saveMember1(Member member,RedirectAttributes redirAttrs) {
		String memberid=memberService.saveMember(member);
		redirAttrs.addFlashAttribute("msg", "Dear Admin, Member added successfully with id "+memberid);
		return "redirect:/newmember1";
	}
	
	@GetMapping("/newmember")
	public String addMember(Model model) {
		model.addAttribute("memberid", memberService.generateMemberId());
		model.addAttribute("insurances", memberService.getInsurances());
		return "newmember";
	}
	
	@PostMapping("/newmember")
	public String saveMember(Member member,RedirectAttributes redirAttrs) {
		String memberid=memberService.saveMember(member);
		redirAttrs.addFlashAttribute("msg", "Dear Admin, Member added successfully with id "+memberid);
		return "redirect:/newmember";
	}
	
	@GetMapping("/update")
	public String updateMember(String member_id,Model model) {
		model.addAttribute("member", memberService.findByMemberId(member_id));
		return "updatemember";
	}
	
	@PostMapping("/update")
	public String updateMemberDetails(Member member,RedirectAttributes redirAttrs) {
		System.out.println(member);
		memberService.updateMember(member);
		redirAttrs.addFlashAttribute("msg", "Dear Admin, Member updated successfully");
		return "redirect:/members";
	}
	
	@GetMapping("/insurances/{id}")
	@ResponseBody
	public Insurances findInsurance(@PathVariable("id") int id) {
		return memberService.getInsurancesById(id);
	}
	
	@GetMapping("/members")
	public String allMembers(Model model) {
		model.addAttribute("list", memberService.getAllMembers());
		return "members";
	}
	
	@GetMapping("/updatemember")
	public String updateMember() {
		return "updatemember";
	}
}
