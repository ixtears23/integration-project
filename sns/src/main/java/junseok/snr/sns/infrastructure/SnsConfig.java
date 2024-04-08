package junseok.snr.sns.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {
    private static final ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create("junseok");

    @Bean
    public SnsClient sqsClient() {
        return SnsClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_NORTHEAST_2)
                .build();
    }

}

