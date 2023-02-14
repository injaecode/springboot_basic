package com.inflearn.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.inflearn.member.domain.Member;
import com.inflearn.member.service.MemberService;
import java.util.List;

@Controller
public class MemberController {
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService=memberService;
		
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		
		return "createMemberForm";
		
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.Join(member);
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String List(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "memberList";
	}
}
