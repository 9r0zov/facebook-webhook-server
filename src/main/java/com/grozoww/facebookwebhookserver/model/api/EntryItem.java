package com.grozoww.facebookwebhookserver.model.api;

import java.util.List;
import lombok.Data;

@Data
public class EntryItem{
	private long time;
	private String id;
	private List<MessagingItem> messaging;
}