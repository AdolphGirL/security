package com.reyes.securityr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reyes.securityr.mapper.MemberAuthorityMapper;
import com.reyes.securityr.model.MemberAuthority;

@Service
public class MemberAuthorityService {
	
	private final MemberAuthorityMapper memberAuthorityMapper;
	
	public MemberAuthorityService(MemberAuthorityMapper memberAuthorityMapper) {
		this.memberAuthorityMapper = memberAuthorityMapper;
	}
	
	@Transactional
	public int insert(MemberAuthority memberAuthority) {
		return memberAuthorityMapper.insert(memberAuthority);
	}
	
	@Transactional(readOnly = true)
	public List<MemberAuthority> findByMemberId(Long memberId) {
		return memberAuthorityMapper.findByMemberId(memberId);
	}
	
}
