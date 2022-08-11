package by.b1.testing.repository.repos;

import by.b1.testing.repository.consts.NamedQueriesKeys;
import by.b1.testing.repository.entity.Turnover;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/** Contains methods to work with turnover table */
@Repository
@ComponentScan("by.b1.testing")
public class TurnoverRepository {
  private final EntityManager entityManager;

  @Autowired
  public TurnoverRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * creates new record in turnover table
   *
   * @param turnover object to insert
   */
  public void create(Turnover turnover) {
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(turnover);
      entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
      entityManager.getTransaction().rollback();
    }
  }

  /**
   * searches for all records in turnover table
   *
   * @return list with all found records
   */
  public List<Turnover> findAll() {
    return entityManager
        .createNamedQuery(NamedQueriesKeys.TURNOVER_FIND_ALL, Turnover.class)
        .getResultList();
  }
}
