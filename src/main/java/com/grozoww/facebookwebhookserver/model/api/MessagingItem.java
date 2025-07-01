package com.grozoww.facebookwebhookserver.model.api;

import lombok.Data;

@Data
public class MessagingItem{
	private Sender sender;
	private Recipient recipient;
	private Message message;
	private long timestamp;
}