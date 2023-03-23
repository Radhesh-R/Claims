package com.claims.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claims.models.ClaimSearchDTO;
import com.claims.models.Claims;
import com.claims.models.Member;
import com.claims.services.ClaimsService;
import com.claims.services.MemberService;

@Controller
public class ClaimsController {
	
	@Autowired MemberService memberService;
	@Autowired ClaimsService claimsService;
	@Autowired HttpSession session;
	@Autowired ServletContext ctx;
     
	@GetMapping("/newrequest1")
	public String newRequest1(String member_id,Model model) {
		System.out.println(member_id);	
		if(member_id!=null) {
			model.addAttribute("memberid", member_id);
			Member member=memberService.findByMemberId(member_id);
			Claims claim=claimsService.findClaimByMemberId(member_id);
			System.out.println(member);
			if(member!=null && claim==null) {
				if(member.getInsurance_type().equals("Home Insurance")) {
					model.addAttribute("reasons", new String[] {"Renovate","Destroyed"});
				}else if(member.getInsurance_type().equals("Vehicle Insurance")) {
					model.addAttribute("reasons", new String[] {"Repair","Stolen"});
				}else {
					model.addAttribute("reasons", new String[] {"Treatment Claim","Death Claim"});
				}
				model.addAttribute("found", true);
				model.addAttribute("member",member);
			}else if(claim!=null){
				model.addAttribute("msg", "Claim already created for this Member Id");
			}else {
				model.addAttribute("msg", "Member Id does not exist");				
			}
		}
		return "newclaimrequest2";
	}
	@PostMapping("/newrequest1")
	public String saveRequest1(Claims c,RedirectAttributes redirAttrs) {
		System.out.println(c);
		LocalDate processdate=c.getReq_date().plusDays(45);
		c.setProcess_date(processdate);
		claimsService.saveClaims(c);
		
		redirAttrs.addFlashAttribute("msg", "Dear Admin,"
				+ "<br>The Claim request for "+c.getMemberid()+" has been posted."
				+ "<br>Your claim will be processed before "+processdate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
		return "redirect:/claims1";
	}
	
	
	@GetMapping("/newrequest")
	public String newRequest(String member_id,Model model) {
		System.out.println(member_id);	
		if(member_id!=null) {
			model.addAttribute("memberid", member_id);
			Member member=memberService.findByMemberId(member_id);
			Claims claim=claimsService.findClaimByMemberId(member_id);
			System.out.println(member);
			if(member!=null && claim==null) {
				if(member.getInsurance_type().equals("Home Insurance")) {
					model.addAttribute("reasons", new String[] {"Renovate","Destroyed"});
				}else if(member.getInsurance_type().equals("Vehicle Insurance")) {
					model.addAttribute("reasons", new String[] {"Repair","Stolen"});
				}else {
					model.addAttribute("reasons", new String[] {"Treatment Claim","Death Claim"});
				}
				model.addAttribute("found", true);
				model.addAttribute("member",member);
			}else if(claim!=null){
				model.addAttribute("msg", "Claim already created for this Member Id");
			}else {
				model.addAttribute("msg", "Member Id does not exist");				
			}
		}
		return "newclaimrequest";
	}
	
	@PostMapping("/newrequest")
	public String saveRequest(Claims c,RedirectAttributes redirAttrs) {
		System.out.println(c);
		LocalDate processdate=c.getReq_date().plusDays(45);
		c.setProcess_date(processdate);
		claimsService.saveClaims(c);
		
		redirAttrs.addFlashAttribute("msg", "Dear Admin,"
				+ "<br>The Claim request for "+c.getMemberid()+" has been posted."
				+ "<br>Your claim will be processed before "+processdate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
		return "redirect:/claims";
	}
	

	@GetMapping("/claims1")
	public String allClaimRequest1(Model model,ClaimSearchDTO dto) {
		System.out.println(dto.getMemberid());
		List<Claims> list=null;
		model.addAttribute("dto", dto);		
		if(dto.getMemberid()!="") {
			System.out.println("Search by memberid");
			list=claimsService.searchClaims().stream()
					.filter(x->x.getMemberid().equals(dto.getMemberid()))
					.collect(Collectors.toList());
		}
		else if(dto.getMname()!="") {
			System.out.println("Search by membername");
			list=claimsService.searchClaims().stream().filter(x->x.getFname().toLowerCase().concat(" ")
						.concat(x.getLname().toLowerCase()).
						contains(dto.getMname().toLowerCase()))
						.collect(Collectors.toList());
		}
		else if(dto.getFrom()!="" && dto.getTo()!="") {
			System.out.println("Search by date");			
			LocalDate fromdate=LocalDate.parse(dto.getFrom());
			LocalDate todate=LocalDate.parse(dto.getTo());
			if(fromdate.isAfter(todate)) {
				model.addAttribute("error", "Incorrect date range seleted");
			}else {
				list=claimsService.searchClaims().stream()
						.filter(x->x.getReq_date().isAfter(fromdate.plusDays(-1)) && x.getReq_date().isBefore(todate.plusDays(1)))
						.collect(Collectors.toList());
			}
		}
		System.out.println(list);
		if(list!=null && list.size()>0) {
			model.addAttribute("found", true);
			model.addAttribute("list",list);
		}else if(list!=null && list.size()==0) {
			model.addAttribute("found", false);			
		}
		return "allclaims2";
	}
	
	@GetMapping("/claims")
	public String allClaimRequest(Model model,ClaimSearchDTO dto) {
		System.out.println(dto.getMemberid());
		List<Claims> list=null;
		model.addAttribute("dto", dto);		
		if(dto.getMemberid()!="") {
			System.out.println("Search by memberid");
			list=claimsService.searchClaims().stream()
					.filter(x->x.getMemberid().equals(dto.getMemberid()))
					.collect(Collectors.toList());
		}
		else if(dto.getMname()!="") {
			System.out.println("Search by membername");
			list=claimsService.searchClaims().stream().filter(x->x.getFname().toLowerCase().concat(" ")
						.concat(x.getLname().toLowerCase()).
						contains(dto.getMname().toLowerCase()))
						.collect(Collectors.toList());
		}
		else if(dto.getFrom()!="" && dto.getTo()!="") {
			System.out.println("Search by date");			
			LocalDate fromdate=LocalDate.parse(dto.getFrom());
			LocalDate todate=LocalDate.parse(dto.getTo());
			if(fromdate.isAfter(todate)) {
				model.addAttribute("error", "Incorrect date range seleted");
			}else {
				list=claimsService.searchClaims().stream()
						.filter(x->x.getReq_date().isAfter(fromdate.plusDays(-1)) && x.getReq_date().isBefore(todate.plusDays(1)))
						.collect(Collectors.toList());
			}
		}
		System.out.println(list);
		if(list!=null && list.size()>0) {
			model.addAttribute("found", true);
			model.addAttribute("list",list);
		}else if(list!=null && list.size()==0) {
			model.addAttribute("found", false);			
		}
		return "allclaims";
	}
	
	@GetMapping("/process")
	public String processRequest(String member_id,Model model) {
		System.out.println(member_id);	
		if(member_id!=null) {
			model.addAttribute("memberid", member_id);
			Member member=memberService.findByMemberId(member_id);
			Claims claim=claimsService.findClaimByMemberId(member_id);
			System.out.println(member);
			if(claim!=null && claim.getStatus().equals("Pending")) {				
				model.addAttribute("found", true);				
				model.addAttribute("member",member);
				model.addAttribute("claim",claim);
			}else if(claim!=null && !claim.getStatus().equals("Pending")) {
				model.addAttribute("msg", "Claim already processed for this Member Id");				
			}else {
				model.addAttribute("msg", "Member Id does not exist");
			}
		}
		return "processclaims";
	}
	
	@PostMapping("/process")
	public String updateClaimProcess(Claims c,MultipartFile doc1,MultipartFile doc2,MultipartFile doc3,RedirectAttributes redirAttrs) {
		claimsService.updateClaims(c);
		if(c.getStatus().equals("Approved")) {
			System.out.println(c);
			String path=ctx.getRealPath("/docs/");
			try {
			Files.copy(doc1.getInputStream(), Paths.get(path+doc1.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			Files.copy(doc2.getInputStream(), Paths.get(path+doc2.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			Files.copy(doc3.getInputStream(), Paths.get(path+doc3.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			}catch(Exception ex) {
				System.err.println("Error "+ex.getMessage());
			}
			claimsService.updateDocs(c.getId(), doc1.getOriginalFilename(), doc2.getOriginalFilename(), doc3.getOriginalFilename());
		}
		redirAttrs.addFlashAttribute("msg", "Claim processed successfully");
		return "redirect:/process";
	}
	
}
