package by.b1.testing.repository.entity;

import by.b1.testing.repository.consts.NamedQueriesKeys;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/** Entity that represents table 'class' */
@Entity(name = "files")
@NamedQueries({
    @NamedQuery(name = NamedQueriesKeys.FILE_FIND_ALL, query = "SELECT f FROM files f"),
    @NamedQuery(name = NamedQueriesKeys.FILE_FIND_BY_NAME, query = "SELECT f FROM files f WHERE f.name = :name")
})
public class File {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "file_id", cascade = CascadeType.ALL)
  private Set<Balance> balances = new HashSet<>();

  @OneToMany(mappedBy = "file_id", cascade = CascadeType.ALL)
  private Set<Turnover> turnovers = new HashSet<>();

  public File() {}

  public File(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Balance> getBalances() {
    return balances;
  }

  public void setBalances(Set<Balance> balances) {
    this.balances = balances;
  }

  public Set<Turnover> getTurnovers() {
    return turnovers;
  }

  public void setTurnovers(Set<Turnover> turnovers) {
    this.turnovers = turnovers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    File file = (File) o;
    return Objects.equals(id, file.id) && Objects.equals(name, file.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "File{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
