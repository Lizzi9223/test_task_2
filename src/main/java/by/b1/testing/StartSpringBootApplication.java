package by.b1.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application entry point
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = "by.b1.testing")
public class StartSpringBootApplication {

  /**
   * starts the app
   * @param args application arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(StartSpringBootApplication.class, args);
  }
}
