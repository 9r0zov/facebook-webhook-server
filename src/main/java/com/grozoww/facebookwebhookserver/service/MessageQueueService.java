package com.grozoww.facebookwebhookserver.service;

import com.grozoww.facebookwebhookserver.client.InstagramMessageClient;
import com.grozoww.facebookwebhookserver.model.KafkaChatMessage;
import com.grozoww.facebookwebhookserver.model.api.InstagramRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageQueueService {

    private final KafkaTemplate<String, KafkaChatMessage> kafkaTemplate;
    private final InstagramHelper instagramHelper;
    private final ReplyBodyService replyBodyService;
    private final InstagramMessageClient instagramMessageClient;

    @SneakyThrows
    public void submitMessage(InstagramRequest request) {
        String typingReply = replyBodyService.getTypingReply(request);
//        instagramMessageClient.typingReply(typingReply);

        KafkaChatMessage kafkaChatMessage = new KafkaChatMessage(
                instagramHelper.getInstagramUserId(request),
                instagramHelper.getInstagramMessageId(request),
                instagramHelper.getInstagramMessage(request));
        kafkaTemplate.send("chat-messages", kafkaChatMessage);
    }

}
