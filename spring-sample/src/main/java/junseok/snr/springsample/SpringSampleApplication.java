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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@EnableAsync
@Slf4j
@SpringBootApplication
public class SpringSampleApplication implements
        CommandLineRunner,
        InitializingBean,
        SmartLifecycle,
        ApplicationListener<ApplicationReadyEvent>,
        ApplicationRunner,
        DisposableBean {

    private final UseComponent useComponent;

    public SpringSampleApplication(UseComponent useComponent) {
        this.useComponent = useComponent;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSampleApplication.class, args);
    }

    @Component
    @Async
    public static class UseComponent {
        public CompletableFuture<String> getNameSleep() {

            try {
                log.info("!!start!!");
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log.info("!!end!!");

            return CompletableFuture.completedFuture("Wake Up!!!");
        }
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
        useComponent.getNameSleep();
        log.info("=== SmartLifecycle.stop...");

    }

    @Override
    public boolean isRunning() {
        log.info("=== SmartLifecycle.isRunning...");
        return running;
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        useComponent.getNameSleep();
        log.info("=== @EventListener - handleContextRefresh...");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        useComponent.getNameSleep();
        log.info("=== ApplicationListener.onApplicationEvent...");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        useComponent.getNameSleep();
        log.info("=== ApplicationRunner.run...");
    }

    @Override
    public void destroy() throws Exception {
        useComponent.getNameSleep();
        log.info("=== DisposableBean.destroy...");
    }
}
