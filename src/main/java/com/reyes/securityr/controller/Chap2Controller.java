package com.reyes.securityr.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reyes.securityr.model.Member;
import com.reyes.securityr.service.MemberService;

@RestController
public class Chap2Controller {

	/** 測試使用，直接將 mapper 拉到 controller */
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	public Chap2Controller(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/members")
	public String createMember(@RequestBody Member member) {
		String encodedPwd = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodedPwd);
		memberService.addMember(member);
		return "Member created successfully";
	}

	@GetMapping("/members")
	public List<Member> getMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("/selected-courses")
	public String selectedCourses() {
		return "修課清單";
	}

	@GetMapping("/course-feedback")
	public String courseFeedback() {
		return "課程回饋";
	}

}
