package by.b1.testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class Service {
  public static void parseExcel() {
    try {
      POIFSFileSystem fs =
          new POIFSFileSystem(
              new FileInputStream("C:\\Users\\User\\Downloads\\ОСВ для тренинга.xls"));
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      HSSFSheet sheet = wb.getSheetAt(0);

      initHeaders(sheet);

      for (int rowNum = getDataStartRow(sheet); rowNum < sheet.getLastRowNum(); rowNum++) {
        Row row = sheet.getRow(rowNum);
        if (Objects.nonNull(row)) {
          Cell firstCell = row.getCell(0);

          if (isSumRow(firstCell)) continue;

          if (isClassRow(firstCell)) {
            // TODO: 011 11.08.22 create class object
            continue;
          }

          Cell billCell = row.getCell(Headers.BILL.getColumnIdx());
          Cell openBalanceAssetCell = row.getCell(Headers.OPENING_BALANCE_ASSETS.getColumnIdx());
          Cell openBalanceLiabilityCell =
              row.getCell(Headers.OPENING_BALANCE_LIABILITY.getColumnIdx());
          Cell turnOverDebitCell = row.getCell(Headers.TURNOVER_DEBIT.getColumnIdx());
          Cell turnOverCreditCell = row.getCell(Headers.TURNOVER_CREDIT.getColumnIdx());
          Cell closeBalanceAssetCell = row.getCell(Headers.CLOSING_BALANCE_ASSETS.getColumnIdx());
          Cell closeBalanceLiabilityCell =
              row.getCell(Headers.CLOSING_BALANCE_LIABILITY.getColumnIdx());

          // TODO: 011 11.08.22 create models

          System.out.println(
              billCell
                  + "\t"
                  + openBalanceAssetCell
                  + "\t"
                  + openBalanceLiabilityCell
                  + "\t"
                  + turnOverDebitCell
                  + "\t"
                  + turnOverCreditCell
                  + "\t"
                  + closeBalanceAssetCell
                  + "\t"
                  + closeBalanceLiabilityCell);
        }
      }

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void initHeaders(HSSFSheet sheet) {
    for (Headers header : Headers.values()) {
      header.setColumnIdx(findColumnByValue(sheet, header.getName(), header.getParentName()));
    }
  }

  private static int getDataStartRow(HSSFSheet sheet) {
    return findRowByValue(sheet, Headers.OPENING_BALANCE_ASSETS.getName()) + 1;
  }

  private static boolean isClassRow(Cell firstCell) {
    return firstCell.getCellType().equals(CellType.STRING)
        && firstCell.getStringCellValue().toLowerCase().contains("КЛАСС ".toLowerCase());
  }

  private static boolean isSumRow(Cell firstCell) {
    if(firstCell.getCellType().equals(CellType.NUMERIC)) return true;
    if(firstCell.getCellType().equals(CellType.STRING)){
      if(firstCell.getStringCellValue().toLowerCase().contains("ПО КЛАССУ".toLowerCase()))
        return true;
      return firstCell.getStringCellValue().toLowerCase().contains("БАЛАНС".toLowerCase());
    }
    return false;
  }

  private static int findColumnByValue(HSSFSheet sheet, String value, String parent) {
    for (Row row : sheet) {
      for (Cell cell : row) {
        if (cell.getCellType() == CellType.STRING) {
          if (cell.getStringCellValue().trim().equalsIgnoreCase(value.trim())) {
            if (Objects.nonNull(parent)) {
              Row parentRow = sheet.getRow(cell.getRowIndex() - 1);
              Cell parentCell = parentRow.getCell(cell.getColumnIndex());
              if (parentCell.getCellType().equals(CellType.BLANK))
                parentCell = parentRow.getCell(cell.getColumnIndex() - 1);
              if (!parentCell.getStringCellValue().trim().equalsIgnoreCase(parent.trim())) continue;
            }
            return cell.getColumnIndex();
          }
        }
      }
    }
    return -1;
  }

  private static int findRowByValue(HSSFSheet sheet, String value) {
    for (Row row : sheet) {
      for (Cell cell : row) {
        if (cell.getCellType() == CellType.STRING) {
          if (cell.getStringCellValue().trim().equalsIgnoreCase(value.trim())) {
            return cell.getRowIndex();
          }
        }
      }
    }
    return -1;
  }
}
