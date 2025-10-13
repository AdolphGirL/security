package com.reyes.securityr.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Member {
	private String id;
	private String username;
	private String password;
	private List<MemberAuthority> authorities = new ArrayList<>();

	public enum MemberAuthority {
		STUDENT, TEACHER, ADMIN
	}
}
