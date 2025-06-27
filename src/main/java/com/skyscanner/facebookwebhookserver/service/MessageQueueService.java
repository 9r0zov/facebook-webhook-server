package com.skyscanner.facebookwebhookserver.service;

import com.skyscanner.facebookwebhookserver.model.KafkaChatMessage;
import com.skyscanner.facebookwebhookserver.model.api.InstagramMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageQueueService {

    private final KafkaTemplate<String, KafkaChatMessage> kafkaTemplate;
    private final InstagramHelper instagramHelper;

    public void submitMessage(InstagramMessage message) {
        KafkaChatMessage kafkaChatMessage = new KafkaChatMessage(
                instagramHelper.getInstagramUserId(message),
                instagramHelper.getInstagramMessageId(message),
                instagramHelper.getInstagramMessage(message));
        kafkaTemplate.send("chat-messages", kafkaChatMessage);
    }

}
