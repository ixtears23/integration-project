package junseok.snr.redisplayground.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisKeySearchStopWatch {
    private final RedisKeySearch redisKeySearch;

    public void findKeysWithKeysCommand() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        redisKeySearch.findKeysWithKeysCommand();
        stopWatch.stop();

        log.info("=== findKeysWithKeysCommand - runningTime : {}", stopWatch.prettyPrint());
    }


    public void findKeysWithScanCommand() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        redisKeySearch.findKeysWithScanCommand();
        stopWatch.stop();

        log.info("=== findKeysWithScanCommand - runningTime : {}", stopWatch.prettyPrint());
    }
}
