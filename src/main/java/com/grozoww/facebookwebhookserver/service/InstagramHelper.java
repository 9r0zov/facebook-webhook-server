package com.grozoww.facebookwebhookserver.service;

import com.grozoww.facebookwebhookserver.model.api.*;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class InstagramHelper {

    public String getInstagramUserId(InstagramRequest instagramRequest) {
        return instagramRequest.getEntry().stream()
                .map(EntryItem::getMessaging)
                .flatMap(Collection::stream)
                .map(MessagingItem::getSender)
                .map(Sender::getId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User ID not found"));
    }

    public String getInstagramMessageId(InstagramRequest instagramRequest) {
        return instagramRequest.getEntry().stream()
                .map(EntryItem::getMessaging)
                .flatMap(Collection::stream)
                .map(MessagingItem::getMessage)
                .map(Message::getMid)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Message ID not found"));
    }

    public String getInstagramMessage(InstagramRequest instagramRequest) {
        return instagramRequest.getEntry().stream()
                .map(EntryItem::getMessaging)
                .flatMap(Collection::stream)
                .map(MessagingItem::getMessage)
                .map(Message::getText)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
    }
}
