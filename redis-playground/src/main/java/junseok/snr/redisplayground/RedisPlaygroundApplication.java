package junseok.snr.redisplayground;

import junseok.snr.redisplayground.search.RedisKeySearchStopWatch;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class RedisPlaygroundApplication implements CommandLineRunner {
    private final RedisKeySearchStopWatch redisKeySearchStopWatch;

    public static void main(String[] args) {
        SpringApplication.run(RedisPlaygroundApplication.class, args);
    }

    @Override
    public void run(String... args) {
        redisKeySearchStopWatch.findKeysWithKeysCommand();

        redisKeySearchStopWatch.findKeysWithScanCommand();

    }
}
