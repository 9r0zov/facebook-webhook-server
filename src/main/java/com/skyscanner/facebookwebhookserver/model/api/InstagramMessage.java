package com.skyscanner.facebookwebhookserver.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstagramMessage {
    private List<EntryItem> entry;
}