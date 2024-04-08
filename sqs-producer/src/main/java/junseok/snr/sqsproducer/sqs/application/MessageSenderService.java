package junseok.snr.sqsproducer.sqs.application;

public interface MessageSenderService {
    void sendMessage(String queueName, String message);
}
