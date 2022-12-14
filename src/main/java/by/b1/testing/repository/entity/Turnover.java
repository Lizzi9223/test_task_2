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

/** Entity that represents table 'turnover' */
@Entity(name = "turnover")
@NamedQueries({
  @NamedQuery(
      name = NamedQueriesKeys.TURNOVER_FIND_ALL,
      query = "SELECT t FROM turnover t WHERE t.file_id = :file ORDER BY t.bank, t.bankClass")
})
public class Turnover {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "class_number")
  private Class bankClass;

  private int bank;
  private BigDecimal debit;
  private BigDecimal credit;

  @Column(name = "from_date")
  private LocalDate from;

  @Column(name = "to_date")
  private LocalDate to;

  @ManyToOne
  @JoinColumn(name = "files_id")
  private File file_id;

  public Turnover() {}

  public Turnover(
      Class bankClass,
      int bank,
      BigDecimal debit,
      BigDecimal credit,
      LocalDate from,
      LocalDate to,
      File file_id) {
    this.bankClass = bankClass;
    this.bank = bank;
    this.debit = debit;
    this.credit = credit;
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

  public int getBank() {
    return bank;
  }

  public void setBank(int bank) {
    this.bank = bank;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
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
    Turnover turnover = (Turnover) o;
    return Objects.equals(id, turnover.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Turnover{"
        + "id="
        + id
        + ", bankClass="
        + bankClass
        + ", bank="
        + bank
        + ", debit="
        + debit
        + ", credit="
        + credit
        + ", from="
        + from
        + ", to="
        + to
        + ", file_id="
        + file_id
        + '}';
  }
}
