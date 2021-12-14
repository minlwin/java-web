package com.jdc.december.post.model.dto;

public record Member(
		String login,
		String name,
		String password,
		Role role
) {
	
	public enum Role {
		Admin, Member
	}

}
