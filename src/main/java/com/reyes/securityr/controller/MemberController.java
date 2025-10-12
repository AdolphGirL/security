package com.reyes.securityr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyes.securityr.model.Member;
import com.reyes.securityr.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

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
}
