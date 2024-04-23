package junseok.snr.virtualthread.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual().start(() -> {
            log.info("=== virtualThread : {}", Thread.currentThread());
        });

        Thread.ofPlatform().start(() -> {
            log.info("=== platform Thread : {}", Thread.currentThread());
        });
    }
}
