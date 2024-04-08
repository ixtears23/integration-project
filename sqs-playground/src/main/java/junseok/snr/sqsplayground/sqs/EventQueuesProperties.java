package junseok.snr.sqsplayground.sqs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@Getter @Setter
@EnableConfigurationProperties(EventQueuesProperties.class)
@ConfigurationProperties(prefix = "events.queues")
public class EventQueuesProperties {
    private String userCreatedByNameQueue;
    private String userCreatedRecordQueue;
    private String userCreatedEventTypeQueue;
}
