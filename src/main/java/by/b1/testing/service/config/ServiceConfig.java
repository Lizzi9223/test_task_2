package by.b1.testing.service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for service layer
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@Configuration
public class ServiceConfig {

  /**
   * Creates ModelMapper bean
   * @return ModelMapper
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
