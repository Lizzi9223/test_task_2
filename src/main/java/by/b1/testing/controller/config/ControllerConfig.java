package by.b1.testing.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan("by.b1.testing")
public class ControllerConfig {
  @Bean
  public MultipartResolver getMultipartResolver(){
    return new CommonsMultipartResolver();
  }
}
