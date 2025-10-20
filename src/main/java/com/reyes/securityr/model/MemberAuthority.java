package com.reyes.securityr.model;

import java.util.Date;

import lombok.Data;

@Data
public class MemberAuthority {

	private Long id;
	private Long memberId;
	private String authority;
	private Date createdAt;

}
