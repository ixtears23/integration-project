package junseok.snr.sqsproducer.sqs.api;

import junseok.snr.sqsproducer.sqs.application.MessageSenderService;
import junseok.snr.sqsproducer.sqs.application.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageSenderController {
    private final MessageSenderService messageSenderService;

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping
    public void sendMessage(@RequestBody SendMessage sendMessage) {
        log.info("=== [API] send - queueName : {}, message : {}", sendMessage.queueName(), sendMessage.message());
        messageSenderService.sendMessage(sendMessage.queueName(), sendMessage.message());
    }
}
