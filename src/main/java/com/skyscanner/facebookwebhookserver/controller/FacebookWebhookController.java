package com.skyscanner.facebookwebhookserver.controller;

import com.skyscanner.facebookwebhookserver.model.api.InstagramMessage;
import com.skyscanner.facebookwebhookserver.service.MessageQueueService;
import com.skyscanner.facebookwebhookserver.service.ReplyBodyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@Slf4j
public class FacebookWebhookController {

    private static final String FB_MODE_SUBSCRIBE = "subscribe";

    private final String verifyToken;
    private final MessageQueueService messageQueueService;
    private final ReplyBodyService replyBodyService;

    public FacebookWebhookController(@Value("${facebook.verify-token}") String verifyToken,
                                     MessageQueueService messageQueueService,
                                     ReplyBodyService replyBodyService) {
        this.verifyToken = verifyToken;
        this.messageQueueService = messageQueueService;
        this.replyBodyService = replyBodyService;
    }

    @GetMapping
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.verify_token") String token,
                                                @RequestParam("hub.challenge") String challenge) {
        if (FB_MODE_SUBSCRIBE.equals(mode) && verifyToken.equals(token)) {
            log.info("Webhook verification successful with challenge: {}", challenge);
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification token mismatch");
        }
    }

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody InstagramMessage instagramMessage) {
        log.info("Received webhook payload: {}", instagramMessage);
        messageQueueService.submitMessage(instagramMessage);
        return ResponseEntity.ok(replyBodyService.getTypingReply(instagramMessage));
    }
}
