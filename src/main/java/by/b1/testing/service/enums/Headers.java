package by.b1.testing.service.enums;

/**
 * Contains excel table headers
 */
public enum Headers {
  BILL("Б/сч", null, -1),
  OPENING_BALANCE_ASSETS("Актив", "ВХОДЯЩЕЕ САЛЬДО", -1),
  OPENING_BALANCE_LIABILITY("Пассив", "ВХОДЯЩЕЕ САЛЬДО", -1),
  TURNOVER_DEBIT("Дебет", "ОБОРОТЫ", -1),
  TURNOVER_CREDIT("Кредит", "ОБОРОТЫ", -1),
  CLOSING_BALANCE_ASSETS("Актив", "ИСХОДЯЩЕЕ САЛЬДО",-1),
  CLOSING_BALANCE_LIABILITY("Пассив", "ИСХОДЯЩЕЕ САЛЬДО",-1);

  private final String name;
  private final String parentName;
  private int columnIdx;
  Headers(String name, String parentName, int columnIdx){
    this.name = name;
    this.parentName = parentName;
    this.columnIdx = columnIdx;
  }
  public String getName(){
    return name;
  }
  public String getParentName(){
    return parentName;
  }
  public int getColumnIdx(){
    return columnIdx;
  }
  public void setColumnIdx(int columnIdx){
    this.columnIdx = columnIdx;
  }

  @Override
  public String toString() {
    return "Headers{" +
        "name='" + name + '\'' +
        ", parentName='" + parentName + '\'' +
        ", columnIdx=" + columnIdx +
        '}';
  }
}
