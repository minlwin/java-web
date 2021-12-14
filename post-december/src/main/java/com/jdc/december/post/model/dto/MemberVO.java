package com.jdc.december.post.model.dto;

import com.jdc.december.post.model.dto.Member.Role;

public record MemberVO(
		String login,
		String name,
		Role role
) {

}
