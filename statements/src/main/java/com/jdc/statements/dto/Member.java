package com.jdc.statements.dto;

import java.time.LocalDate;

public record Member(
		String email,
		String name, 
		String password,
		LocalDate dob,
		Role role
		) {
	
	public enum Role {
		Admin, Member
	}
}
