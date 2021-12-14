package com.jdc.message.model.dto;

import java.time.LocalDateTime;

public record Message(
		int id,
		String title,
		String message,
		Member member,
		LocalDateTime creation
		) {

	public static Message newMessage(String login, String title, String message) {
		return new Message(0, title, message, Member.onlyLogin(login), null);
	}
}
