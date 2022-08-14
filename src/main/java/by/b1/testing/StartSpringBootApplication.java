package by.b1.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "by.b1.testing")
public class StartSpringBootApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartSpringBootApplication.class, args);
  }
}
