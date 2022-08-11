package by.b1.testing.repository.entity;

import by.b1.testing.repository.consts.NamedQueriesKeys;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity that represents table 'balance'
 */
@Entity(name = "balance")
@NamedQueries({
  @NamedQuery(name = NamedQueriesKeys.BALANCE_FIND_ALL, query = "SELECT b FROM balance b")
})
public class Balance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "class_number")
  private Class bankClass;

  private String type;
  private int bank;
  private BigDecimal asset;
  private BigDecimal liability;

  @Column(name = "from_date")
  private LocalDate from;

  @Column(name = "to_date")
  private LocalDate to;

  public Balance() {}

  public Balance(
      Class bankClass,
      String type,
      int bank,
      BigDecimal asset,
      BigDecimal liability,
      LocalDate from,
      LocalDate to) {
    this.bankClass = bankClass;
    this.type = type;
    this.bank = bank;
    this.asset = asset;
    this.liability = liability;
    this.from = from;
    this.to = to;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Class getBankClass() {
    return bankClass;
  }

  public void setBankClass(Class bankClass) {
    this.bankClass = bankClass;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getBank() {
    return bank;
  }

  public void setBank(int bank) {
    this.bank = bank;
  }

  public BigDecimal getAsset() {
    return asset;
  }

  public void setAsset(BigDecimal asset) {
    this.asset = asset;
  }

  public BigDecimal getLiability() {
    return liability;
  }

  public void setLiability(BigDecimal liability) {
    this.liability = liability;
  }

  public LocalDate getFrom() {
    return from;
  }

  public void setFrom(LocalDate from) {
    this.from = from;
  }

  public LocalDate getTo() {
    return to;
  }

  public void setTo(LocalDate to) {
    this.to = to;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Balance balance = (Balance) o;
    return Objects.equals(id, balance.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Balance{"
        + "id="
        + id
        + ", bankClass="
        + bankClass
        + ", type='"
        + type
        + '\''
        + ", bank="
        + bank
        + ", asset="
        + asset
        + ", liability="
        + liability
        + ", from="
        + from
        + ", to="
        + to
        + '}';
  }
}
