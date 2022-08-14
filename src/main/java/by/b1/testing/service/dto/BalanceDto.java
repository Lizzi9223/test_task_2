package by.b1.testing.service.dto;

import by.b1.testing.repository.entity.Class;
import by.b1.testing.repository.entity.File;
import by.b1.testing.repository.enums.BalanceType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Data transfer object for file entity
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
public class BalanceDto {
  private Long id;
  private Class bankClass;
  private BalanceType type;
  private int bank;
  private BigDecimal asset;
  private BigDecimal liability;
  private LocalDate from;
  private LocalDate to;
  private File file_id;

  public BalanceDto() {}

  public BalanceDto(
      Long id,
      Class bankClass,
      BalanceType type,
      int bank,
      BigDecimal asset,
      BigDecimal liability,
      LocalDate from,
      LocalDate to,
      File file_id) {
    this.id = id;
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
    return asset.setScale(2, RoundingMode.HALF_UP);
  }

  public void setAsset(BigDecimal asset) {
    this.asset = asset;
  }

  public BigDecimal getLiability() {
    return liability.setScale(2, RoundingMode.HALF_UP);
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
    BalanceDto that = (BalanceDto) o;
    return bank == that.bank
        && Objects.equals(id, that.id)
        && Objects.equals(bankClass, that.bankClass)
        && type == that.type
        && Objects.equals(asset, that.asset)
        && Objects.equals(liability, that.liability)
        && Objects.equals(from, that.from)
        && Objects.equals(to, that.to)
        && Objects.equals(file_id, that.file_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bankClass, type, bank, asset, liability, from, to, file_id);
  }

  @Override
  public String toString() {
    return "BalanceDto{"
        + "id="
        + id
        + ", bankClass="
        + bankClass
        + ", type="
        + type
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
        + ", file_id="
        + file_id
        + '}';
  }
}
