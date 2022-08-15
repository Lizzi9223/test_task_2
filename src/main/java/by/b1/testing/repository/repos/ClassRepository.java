package by.b1.testing.repository.repos;

import by.b1.testing.repository.entity.Class;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * Contains methods to work with class table
 */
@Repository
@ComponentScan("by.b1.testing")
public class ClassRepository {
  private final EntityManager entityManager;

  @Autowired
  public ClassRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * creates new record in class table
   * @param bankClass object to insert
   */
  public void create(Class bankClass) {
    try {
      entityManager.getTransaction().begin();
      entityManager.clear();
      entityManager.persist(bankClass);
      entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
      entityManager.getTransaction().rollback();
    }
  }
}
