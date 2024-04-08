package junseok.snr.sns.application;

import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationService {
    private static final String TOPIC = "order-topic";
    private final SnsTemplate snsTemplate;

    public void sendNotification(SendNotificationRequest request) {
        snsTemplate.sendNotification(TOPIC, request.message(), request.subject());
    }

}
