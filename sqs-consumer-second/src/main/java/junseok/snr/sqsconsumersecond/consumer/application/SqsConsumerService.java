package junseok.snr.sqsconsumersecond.consumer.application;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SqsConsumerService {
    private static final String ORDER_002_QUEUE = "order-002-queue";

    @SqsListener(ORDER_002_QUEUE)
    public void receiveMessage(String message) {
        log.info("=== received Message : queueName : {}, {}", ORDER_002_QUEUE,  message);
    }
}
