package com.grozoww.facebookwebhookserver.listener;

import com.grozoww.facebookwebhookserver.client.InstagramMessageClient;
import com.grozoww.facebookwebhookserver.model.KafkaChatMessage;
import com.grozoww.facebookwebhookserver.service.ReplyBodyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class KafkaChatListener {

    private final ReplyBodyService replyBodyService;
    private final InstagramMessageClient instagramMessageClient;

    @KafkaListener(
            topics = "chat-responses",
            groupId = "mcp-host-group",
            containerFactory = "kafkaChatMessageListenerContainerFactory")
    @SneakyThrows
    public void listen(KafkaChatMessage message) {
        log.info("Received response message {}", message);
        String replyBody = replyBodyService.getTextReply(message.getUserId(), message.getMessage());
        instagramMessageClient.replyToUser(replyBody);
    }
}
