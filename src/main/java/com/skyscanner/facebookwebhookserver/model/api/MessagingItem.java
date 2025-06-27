package com.skyscanner.facebookwebhookserver.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagingItem {
	private Sender sender;
	private Message message;
}
