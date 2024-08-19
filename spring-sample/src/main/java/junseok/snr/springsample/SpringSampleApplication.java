package junseok.snr.springsample;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
public class SpringSampleApplication implements
        CommandLineRunner,
        InitializingBean,
        SmartLifecycle,
        ApplicationListener<ApplicationReadyEvent>,
        ApplicationRunner,
        DisposableBean {

    public static void main(String[] args) {
        SpringApplication.run(SpringSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("=== CommandLineRunner.run...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("=== InitializingBean.afterPropertiesSet...");
    }

    @PostConstruct
    public void init() {
        log.info("=== PostConstruct...");
    }

    private boolean running = false;

    @Override
    public void start() {
        running = true;
        log.info("=== SmartLifecycle.start...");
    }

    @Override
    public void stop() {
        running = false;
        log.info("=== SmartLifecycle.stop...");

    }

    @Override
    public boolean isRunning() {
        log.info("=== SmartLifecycle.isRunning...");
        return running;
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        log.info("=== @EventListener - handleContextRefresh...");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("=== ApplicationListener.onApplicationEvent...");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=== ApplicationRunner.run...");
    }

    @Override
    public void destroy() throws Exception {
        log.info("=== DisposableBean.destroy...");
    }
}
