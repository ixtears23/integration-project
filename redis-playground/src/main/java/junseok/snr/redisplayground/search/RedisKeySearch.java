package junseok.snr.redisplayground.search;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisKeySearch {
    private final RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void createTestKeys() throws InterruptedException {

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            executorService.submit(() -> {
                for (int j = threadNum * 100_000; j < (threadNum + 1) * 10_000; j++) {
                    redisTemplate.opsForSet().add("test:" + j, "value:" + j);
                }
            });
        }

        executorService.shutdown();;
        executorService.awaitTermination(100, TimeUnit.SECONDS);

    }

    public void findKeysWithKeysCommand() {
        final Set<String> keys = redisTemplate.keys("test:*");
        log(keys);
    }

    private static void log(Set<String> keys) {
        log.info("=== key size : {}", Objects.requireNonNull(keys).size());
    }

    public void findKeysWithScanCommand() {
        final Set<String> keys = redisTemplate.execute((RedisCallback<Set<String>>) action -> {
            final Set<String> keyTemporary = new HashSet<>();
            final Cursor<byte[]> cursor = action.scan(ScanOptions.scanOptions()
                    .match("test:*").count(100)
                    .build());
            while (cursor.hasNext()) {
                keyTemporary.add(new String(cursor.next()));
            }
            return keyTemporary;
        });
        log(keys);
    }

}
