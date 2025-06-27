package com.skyscanner.facebookwebhookserver.service;

import com.skyscanner.facebookwebhookserver.model.api.*;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class InstagramHelper {

    public String getInstagramUserId(InstagramMessage instagramMessage) {
        return instagramMessage.getEntry().stream()
                .map(EntryItem::getMessaging)
                .flatMap(Collection::stream)
                .map(MessagingItem::getSender)
                .map(Sender::getId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User ID not found"));
    }

    public String getInstagramMessageId(InstagramMessage instagramMessage) {
        return instagramMessage.getEntry().stream()
                .map(EntryItem::getMessaging)
                .flatMap(Collection::stream)
                .map(MessagingItem::getMessage)
                .map(Message::getMid)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Message ID not found"));
    }

    public String getInstagramMessage(InstagramMessage instagramMessage) {
        return instagramMessage.getEntry().stream()
                .map(EntryItem::getMessaging)
                .flatMap(Collection::stream)
                .map(MessagingItem::getMessage)
                .map(Message::getText)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
    }
}
