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

	@Transactional(readOnly = true)
	public List<Member> findAll() {
		return memberMapper.findAll();
	}
	
	@Transactional
	public int insert(Member member) {
		return memberMapper.insert(member);
	}
	
}
