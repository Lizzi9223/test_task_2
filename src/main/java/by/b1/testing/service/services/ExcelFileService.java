package by.b1.testing.service.services;

import by.b1.testing.service.enums.Headers;
import by.b1.testing.service.utils.Converter;
import java.time.LocalDate;
import java.util.Objects;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

/**
 * Contains methods to work with excel file
 *
 * @author Lizaveta Yakauleva
 * @version 1.0
 */
@Service
public class ExcelFileService {

  /**
   * initializes {@code Headers} enum with data
   *
   * @param sheet excel sheet to work with
   */
  public void initHeaders(HSSFSheet sheet) {
    for (Headers header : Headers.values()) {
      header.setColumnIdx(findColumnByValue(sheet, header.getName(), header.getParentName()));
    }
  }

  /**
   * receives time range from file
   *
   * @param sheet excel sheet to work with
   * @return array containing left and right time range borders
   */
  public LocalDate[] getTimeRange(HSSFSheet sheet) {
    Row timeRangeRow = sheet.getRow(findRowByValue(sheet, "период"));
    Cell timeRangeCell = timeRangeRow.getCell(0);
    return Converter.extractTimeRangeFromString(timeRangeCell.getStringCellValue());
  }

  /**
   * gets row index where the main content starts
   *
   * @param sheet excel sheet to work with
   * @return row index where the content data starts
   */
  public int getDataStartRow(HSSFSheet sheet) {
    return findRowByValue(sheet, Headers.OPENING_BALANCE_ASSETS.getName()) + 1;
  }

  /**
   * checks if provided cell's row contains class
   *
   * @param firstCell cell to check
   * @return true if provided cell's row contains class, false otherwise
   */
  public boolean isClassRow(Cell firstCell) {
    return firstCell.getCellType().equals(CellType.STRING)
        && firstCell.getStringCellValue().toLowerCase().contains("КЛАСС ".toLowerCase());
  }

  /**
   * checks if provided cell's row contains final counts
   *
   * @param firstCell cell to check
   * @return true if provided cell's row contains final counts, false otherwise
   */
  public boolean isSumRow(Cell firstCell) {
    if (firstCell.getCellType().equals(CellType.NUMERIC)) return true;
    if (firstCell.getCellType().equals(CellType.STRING)) {
      if (firstCell.getStringCellValue().toLowerCase().contains("ПО КЛАССУ".toLowerCase()))
        return true;
      return firstCell.getStringCellValue().toLowerCase().contains("БАЛАНС".toLowerCase());
    }
    return false;
  }

  /**
   * searches for column by value
   *
   * @param sheet excel sheet to work with
   * @param value value to search by
   * @param parent value of the column above desired
   * @return index of found column
   */
  public int findColumnByValue(HSSFSheet sheet, String value, String parent) {
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

  /**
   * searches for row by value
   *
   * @param sheet excel sheet to work with
   * @param value value to search by
   * @return index of found row
   */
  public int findRowByValue(HSSFSheet sheet, String value) {
    for (Row row : sheet) {
      for (Cell cell : row) {
        if (cell.getCellType() == CellType.STRING) {
          if (cell.getStringCellValue().trim().toLowerCase().contains(value.trim().toLowerCase())) {
            return cell.getRowIndex();
          }
        }
      }
    }
    return -1;
  }
}
