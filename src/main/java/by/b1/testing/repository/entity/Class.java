package by.b1.testing.repository.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity that represents table 'class'
 */
@Entity(name = "class")
public class Class {
  @Id private Long number;

  private String description;

  @OneToMany(mappedBy = "bankClass", cascade = CascadeType.ALL)
  private Set<Balance> balances = new HashSet<>();

  @OneToMany(mappedBy = "bankClass", cascade = CascadeType.ALL)
  private Set<Turnover> turnovers = new HashSet<>();

  public Class() {}

  public Class(Long number, String description) {
    this.number = number;
    this.description = description;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
    Class aClass = (Class) o;
    return Objects.equals(number, aClass.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }

  @Override
  public String toString() {
    return "Class{" + "number=" + number + ", description='" + description + '\'' + '}';
  }
}
