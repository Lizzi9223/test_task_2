package by.b1.testing.repository.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for repository layer
 */
@Configuration
@ComponentScan("by.b1.testing")
public class RepositoryConfig{

  /**
   * creates EntityManager bean
   * @return entity manager
   */
  @Bean
  public EntityManager getEntityManager(){
    return Persistence.createEntityManagerFactory( "test_task_2").createEntityManager();
  }
}
