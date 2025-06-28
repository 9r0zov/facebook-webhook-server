package com.skyscanner.facebookwebhookserver.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Log4j2
@RequiredArgsConstructor
public class InstagramMessageClient {

    private final RestClient instagramClient;

    public void replyToUser(String replyBody) {
        log.info("Sending LLM reply {}", replyBody);
        instagramClient
                .post()
                .body(replyBody)
                .retrieve()
                .toBodilessEntity();
    }

    public void typingReply(String typingReply) {
        log.info("Sending typing reply {}", typingReply);
        instagramClient.post()
                .body(typingReply)
                .retrieve()
                .toBodilessEntity();
    }
}
