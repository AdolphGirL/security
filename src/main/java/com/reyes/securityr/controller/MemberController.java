package com.reyes.securityr.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reyes.securityr.model.Member;
import com.reyes.securityr.model.MemberAuthority;
import com.reyes.securityr.service.MemberAuthorityService;
import com.reyes.securityr.service.MemberService;

@RestController
public class MemberController {
	
	private final PasswordEncoder passwordEncoder;

	private final MemberService memberService;
	
	private final MemberAuthorityService memberAuthorityService;
	
	public MemberController(PasswordEncoder passwordEncoder, MemberService memberService,
			MemberAuthorityService memberAuthorityService) {
		this.passwordEncoder = passwordEncoder;
		this.memberService = memberService;
		this.memberAuthorityService = memberAuthorityService;
	}
	
	@PostMapping("/members")
	public String createMember(@RequestBody Member member) {
		member.setId(null);
		
		/** Encode the password before saving the member */
		String encodedPwd = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodedPwd);
		
		this.memberService.insert(member);
		
		MemberAuthority authority = new MemberAuthority();
		authority.setMemberId(member.getId());
		
		String[] roles = { "ADMIN", "TEACHER", "STUDENT" };
		String randomRole = roles[new java.util.Random().nextInt(roles.length)];
		authority.setAuthority(randomRole);
		authority.setCreatedAt(new java.util.Date());
		
		this.memberAuthorityService.insert(authority);
		
		return "success, member: " + member.getUsername() + " assigned authority = " + randomRole;
	}

	@GetMapping("/members")
	public List<Member> getMembers() {
		return this.memberService.findAll();
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
