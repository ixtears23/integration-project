package junseok.snr.sqsproducer.sqs.application;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SqsProducerService {
    private static final String SEND_MESSAGE_QUEUE = "send-message-queue";
    private static final String ORDER_001_QUEUE = "order-002-queue";

    @SqsListener(SEND_MESSAGE_QUEUE)
    public void listen(String message) {
        logMessage(SEND_MESSAGE_QUEUE, message);
    }

    @SqsListener(ORDER_001_QUEUE)
    public void receiveMessage(String message) {
        logMessage(ORDER_001_QUEUE, message);
    }

    private static void logMessage(String queueName, String message) {
        log.info("=== received Message : queueName : {}, {}", queueName, message);
    }

}
