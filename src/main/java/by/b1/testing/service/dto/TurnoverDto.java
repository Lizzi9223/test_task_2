package by.b1.testing.service.dto;

import by.b1.testing.repository.entity.Class;
import by.b1.testing.repository.entity.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Data transfer object for turnover entity
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class TurnoverDto {
  private Long id;
  private Class bankClass;
  private int bank;
  private BigDecimal debit;
  private BigDecimal credit;
  private LocalDate from;
  private LocalDate to;
  private File file_id;

  public TurnoverDto() {}

  public TurnoverDto(
      Long id,
      Class bankClass,
      int bank,
      BigDecimal debit,
      BigDecimal credit,
      LocalDate from,
      LocalDate to,
      File file_id) {
    this.id = id;
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
    return debit.setScale(2, RoundingMode.HALF_UP);
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getCredit() {
    return credit.setScale(2, RoundingMode.HALF_UP);
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
    TurnoverDto that = (TurnoverDto) o;
    return bank == that.bank
        && Objects.equals(id, that.id)
        && Objects.equals(bankClass, that.bankClass)
        && Objects.equals(debit, that.debit)
        && Objects.equals(credit, that.credit)
        && Objects.equals(from, that.from)
        && Objects.equals(to, that.to)
        && Objects.equals(file_id, that.file_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bankClass, bank, debit, credit, from, to, file_id);
  }

  @Override
  public String toString() {
    return "TurnoverDto{"
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
