package junseok.snr.sqsconsumer.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SqsService {

    @SqsListener("send-message-queue")
    public void listen(String message) {
        log.info("=== listen - message : {}", message);
    }

}
