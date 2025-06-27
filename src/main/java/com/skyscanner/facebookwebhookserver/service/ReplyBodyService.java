package com.skyscanner.facebookwebhookserver.service;

import com.skyscanner.facebookwebhookserver.model.api.InstagramMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyBodyService {

    private final InstagramHelper instagramHelper;

    public String getTypingReply(InstagramMessage instagramMessage) {
        var userId = instagramHelper.getInstagramUserId(instagramMessage);
        return """
                {
                  "messaging_product": "instagram",
                  "recipient": {"id":"%s"},
                  "sender_action": "typing_on"
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
