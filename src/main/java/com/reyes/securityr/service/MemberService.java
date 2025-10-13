package com.reyes.securityr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reyes.securityr.mapper.MemberMapper;
import com.reyes.securityr.model.Member;

@Service
public class MemberService {

	private final MemberMapper memberMapper;

	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	public List<Member> getAllMembers() {
		return memberMapper.findAll();
	}

	public Member getMember(String id) {
		return memberMapper.findById(id);
	}

	@Transactional
	public void addMember(Member member) {
		memberMapper.insert(member);
	}

	@Transactional
	public void deleteMember(String id) {
		memberMapper.delete(id);
	}
}
