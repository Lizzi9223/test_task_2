package by.b1.testing.repository.repos;

import by.b1.testing.repository.consts.NamedQueriesKeys;
import by.b1.testing.repository.consts.Parameters;
import by.b1.testing.repository.entity.File;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/** Contains methods to work with file table */
@Repository
@ComponentScan("by.b1.testing")
public class FileRepository {
  private final EntityManager entityManager;

  @Autowired
  public FileRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * creates new record in files table
   *
   * @param file object to insert
   */
  public void create(File file) {
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(file);
      entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
      entityManager.getTransaction().rollback();
      throw e;
    }
  }

  /**
   * searches for all records in files table
   *
   * @return list with all found records
   */
  public List<File> findAll() {
    return entityManager
        .createNamedQuery(NamedQueriesKeys.FILE_FIND_ALL, File.class)
        .getResultList();
  }

  /**
   * searches for file by its name
   *
   * @return list with all found records
   */
  public File findByName(String name) {
    return entityManager
        .createNamedQuery(NamedQueriesKeys.FILE_FIND_BY_NAME, File.class)
        .setParameter(Parameters.NAME, name)
        .getSingleResult();
  }
}
