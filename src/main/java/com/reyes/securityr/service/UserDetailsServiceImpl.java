package com.reyes.securityr.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reyes.securityr.mapper.MemberAuthorityMapper;
import com.reyes.securityr.mapper.MemberMapper;
import com.reyes.securityr.model.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MemberMapper memberMapper;

	private final MemberAuthorityMapper memberAuthorityMapper;

	public UserDetailsServiceImpl(MemberMapper memberMapper, MemberAuthorityMapper memberAuthorityMapper) {
		this.memberMapper = memberMapper;
		this.memberAuthorityMapper = memberAuthorityMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = this.memberMapper.findByUsername(username);
		if (member == null) {
			throw new UsernameNotFoundException("Can't find member: " + username);
		}

		/** * Load member authorities */
		Optional.ofNullable(this.memberAuthorityMapper.findByMemberId(member.getId())).ifPresent(authorities -> {
			authorities.forEach(authority -> {
				member.addAuthority(authority.getAuthority());
			});
		});
		
		List<SimpleGrantedAuthority> authorities = 
				Optional.ofNullable(member.getAuthorities()).orElse(Collections.emptyList())
					.stream().filter(Objects::nonNull)
					.map(auth -> new SimpleGrantedAuthority(auth)).toList();
		
		return User.withUsername(username)
				.password(member.getPassword()).authorities(authorities).build();
	}

}
