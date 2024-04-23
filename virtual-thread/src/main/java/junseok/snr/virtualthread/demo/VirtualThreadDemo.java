package junseok.snr.virtualthread.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread = Thread.ofVirtual().start(() -> {
            log.info("=== virtualThread : {}", Thread.currentThread());
        });

        thread.join();

        Thread.ofPlatform().start(() -> {
            log.info("=== platform Thread : {}", Thread.currentThread());
        });
    }
}
