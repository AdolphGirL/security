package com.reyes.securityr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyes.securityr.service.MemberService;

@RestController
@RequestMapping("/members")
public class Chap1Controller {
	
	
	private final MemberService memberService;

	public Chap1Controller(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	@GetMapping
	public List<Member> getAllMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("/{id}")
	public Member getMember(@PathVariable String id) {
		return memberService.getMember(id);
	}

	@PostMapping
	public String addMember(@RequestBody Member member) {
		memberService.addMember(member);
		return "Member added successfully";
	}

	@DeleteMapping("/{id}")
	public String deleteMember(@PathVariable String id) {
		memberService.deleteMember(id);
		return "Member deleted successfully";
	}
	**/
	
}
