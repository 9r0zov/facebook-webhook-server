package com.skyscanner.facebookwebhookserver.model.api;

import lombok.Data;

@Data
public class Message{
	private String mid;
	private String text;
	private boolean isEcho;
}