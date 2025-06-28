package com.skyscanner.facebookwebhookserver.controller;

import com.skyscanner.facebookwebhookserver.model.api.InstagramRequest;
import com.skyscanner.facebookwebhookserver.service.MessageQueueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@Log4j2
public class FacebookWebhookController {

    private static final String FB_MODE_SUBSCRIBE = "subscribe";

    private final String verifyToken;
    private final MessageQueueService messageQueueService;

    public FacebookWebhookController(@Value("${facebook.verify-token}") String verifyToken,
                                     MessageQueueService messageQueueService) {
        this.verifyToken = verifyToken;
        this.messageQueueService = messageQueueService;
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
    public ResponseEntity<String> handleWebhook(@RequestBody InstagramRequest instagramRequest) {
        log.info("Received webhook payload: {}", instagramRequest);
        try {
            messageQueueService.submitMessage(instagramRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok("EVENT_RECEIVED");
    }
}
