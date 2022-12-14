package by.b1.testing.repository.entity;

import by.b1.testing.repository.consts.NamedQueriesKeys;
import by.b1.testing.repository.enums.BalanceType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/** Entity that represents table 'balance' */
@Entity(name = "balance")
@NamedQueries({
  @NamedQuery(
      name = NamedQueriesKeys.BALANCE_FIND_ALL,
      query = "SELECT b FROM balance b WHERE b.file_id = :file ORDER BY b.bank, b.file_id, b.bankClass")
})
public class Balance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "class_number")
  private Class bankClass;

  @Enumerated(EnumType.STRING)
  private BalanceType type;
  private int bank;
  private BigDecimal asset;
  private BigDecimal liability;

  @Column(name = "from_date")
  private LocalDate from;

  @Column(name = "to_date")
  private LocalDate to;

  @ManyToOne
  @JoinColumn(name = "files_id")
  private File file_id;

  public Balance() {}

  public Balance(
      Class bankClass,
      BalanceType type,
      int bank,
      BigDecimal asset,
      BigDecimal liability,
      LocalDate from,
      LocalDate to,
      File file_id) {
    this.bankClass = bankClass;
    this.type = type;
    this.bank = bank;
    this.asset = asset;
    this.liability = liability;
    this.from = from;
    this.to = to;
    this.file_id = file_id;
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

  public BalanceType getType() {
    return type;
  }

  public void setType(BalanceType type) {
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

  public File getFile_id() {
    return file_id;
  }

  public void setFile_id(File file_id) {
    this.file_id = file_id;
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
    return "Balance{" +
        "id=" + id +
        ", bankClass=" + bankClass +
        ", type=" + type +
        ", bank=" + bank +
        ", asset=" + asset +
        ", liability=" + liability +
        ", from=" + from +
        ", to=" + to +
        ", file_id=" + file_id +
        '}';
  }
}
