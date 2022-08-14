package by.b1.testing.repository.repos;

import by.b1.testing.repository.consts.NamedQueriesKeys;
import by.b1.testing.repository.consts.Parameters;
import by.b1.testing.repository.entity.Balance;
import by.b1.testing.repository.entity.File;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * Contains methods to work with balance table
 */
@Repository
@ComponentScan("by.b1.testing")
public class BalanceRepository {
  private final EntityManager entityManager;

  @Autowired
  public BalanceRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * creates new record in balance table
   * @param balance object to insert
   */
  public void create(Balance balance) {
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(balance);
      entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
      entityManager.getTransaction().rollback();
    }
  }

  /**
   * searches for all records in balance table by file id
   * @return list with all found records
   */
  public List<Balance> findByFileId(File file) {
    return entityManager
        .createNamedQuery(NamedQueriesKeys.BALANCE_FIND_ALL, Balance.class)
        .setParameter(Parameters.FILE, file)
        .getResultList();
  }
}
