package junseok.snr.sqsproducer.sqs.application;

public record SendMessage(String queueName,
                          String message) {
}
