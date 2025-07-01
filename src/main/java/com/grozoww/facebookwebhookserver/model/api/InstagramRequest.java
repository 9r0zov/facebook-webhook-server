package com.grozoww.facebookwebhookserver.model.api;

import java.util.List;
import lombok.Data;

@Data
public class InstagramRequest{
	private List<EntryItem> entry;
	private String object;
}