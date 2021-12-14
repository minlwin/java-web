package com.jdc.message.model.dto;

public record Member(
		String login,
		String name,
		String role
		) {
	
	public static Member onlyLogin(String login) {
		return new Member(login, null, null);
	}
}
