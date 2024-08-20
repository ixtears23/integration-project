package junseok.snr.springsample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@RequestMapping("/v1/thread")
@RestController
public class ThreadController {
    private final SpringSampleApplication.UseComponent useComponent;

    public ThreadController(SpringSampleApplication.UseComponent useComponent) {
        this.useComponent = useComponent;
    }

    @PostMapping("/process")
    public void processThread() {
        useComponent.getNameSleep();
    }


    @PostMapping("/process-future-parallel")
    public void processThreadFutureParallel() {
        Random random = new Random();
        log.info("=== process-future");


        IntStream.range(1, 7)
                .parallel()
                .forEach(i -> {
                    try {
                        log.info("=== process-future");
                        Thread.sleep(random.nextInt(i) * 1_000);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        log.info("=== process-future end");
    }

    @PostMapping("/process-future")
    public void processThreadFuture() {
        Random random = new Random();
        log.info("=== process-future");


        IntStream.range(1, 7)
                .forEach(i -> {
                    try {
                        log.info("=== process-future");
                        Thread.sleep(random.nextInt(i) * 1_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        log.info("=== process-end");
    }

}
