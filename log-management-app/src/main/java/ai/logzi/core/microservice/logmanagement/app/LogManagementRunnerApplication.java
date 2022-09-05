package ai.logzi.core.microservice.logmanagement.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ai.logzi"})
public class LogManagementRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogManagementRunnerApplication.class, args);
    }
}
