package com.skyscanner.facebookwebhookserver.service;

import com.skyscanner.facebookwebhookserver.model.api.InstagramRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyBodyService {

    private final InstagramHelper instagramHelper;

    public String getTypingReply(InstagramRequest instagramRequest) {
        var userId = instagramHelper.getInstagramUserId(instagramRequest);
        return """
                {
                  "messaging_product": "instagram",
                  "recipient": {"id":"%s"},
                  "sender_action": "TYPING_ON"
                }
                """.formatted(userId);
    }

    public String getTextReply(String userId, String llmReply) {
        return """
                {
                  "messaging_product": "instagram",
                  "recipient": {"id":"%s"},
                  "message": {
                    "text": "%s"
                  }
                }""".formatted(userId, llmReply);
    }

}
