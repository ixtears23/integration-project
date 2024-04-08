package junseok.snr.sqsproducer.sqs.application;

import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageSenderServiceImpl implements MessageSenderService {
    private final SqsTemplate sqsTemplate;

    @Override
    public void sendMessage(String queueName, String message) {
        final SendResult<String> sendResult = sqsTemplate.send(queueName, message);

        log.info("=== [Application] messageId : {}", sendResult.messageId());
        log.info("=== [Application] endpoint : {}", sendResult.endpoint());
        log.info("=== [Application] message : {}", sendResult.message());
    }
}
