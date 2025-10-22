package com.reyes.securityr.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Member {

	private Long id;
	private String username;
	private String password;
	private Boolean enabled;
	private Date createdAt;
	
	private List<String> authorities;
	
	public void addAuthority(String authority) {
		if (this.authorities == null) {
			this.authorities = new java.util.ArrayList<>();
		}
		
		this.authorities.add(authority);
	}

}
